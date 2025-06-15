// Dashboard screen - Shows a list of items and handles user navigation
package com.example.api_project_ritesh.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_project_ritesh.databinding.ActivityDashboardBinding
import com.example.api_project_ritesh.ui.details.DetailsActivity
import com.example.api_project_ritesh.ui.login.LoginActivity
import com.example.api_project_ritesh.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

// Enable Hilt dependency injection for this activity
@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    // Binding to access UI elements
    private lateinit var binding: ActivityDashboardBinding
    
    // ViewModel to handle data and business logic
    private val viewModel: DashboardViewModel by viewModels()
    
    // Adapter to display the list of items
    private lateinit var adapter: EntityAdapter

    // Key to pass data between activities
    companion object {
        const val EXTRA_KEYPASS = "extra_keypass"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set up the UI
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up back button in toolbar
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set up logout button - returns to login screen
        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            // Clear all previous activities from stack
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Initialize the list and load data
        setupRecyclerView()
        setupObservers()
        loadDashboard()
    }

    // Set up the RecyclerView to display the list of items
    private fun setupRecyclerView() {
        // Create adapter with click listener for each item
        adapter = EntityAdapter { entity ->
            // When an item is clicked, store it and open details screen
            viewModel.selectEntity(entity)
            val map = HashMap<String, Any?>(entity)
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(DetailsActivity.EXTRA_ENTITY, map)
            }
            startActivity(intent)
        }

        // Configure RecyclerView with adapter and layout
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = this@DashboardActivity.adapter
        }
    }

    // Set up observers to watch for data changes
    private fun setupObservers() {
        // Watch for dashboard data updates
        viewModel.dashboardData.observe(this) { result ->
            result.fold(
                // On success, update the list and hide error
                onSuccess = { response ->
                    adapter.submitList(response.entities)
                    binding.errorTextView.visibility = View.GONE
                },
                // On error, show error message
                onFailure = { error ->
                    binding.errorTextView.apply {
                        text = error.message
                        visibility = View.VISIBLE
                    }
                }
            )
        }

        // Watch for loading state changes
        viewModel.isLoading.observe(this) { isLoading ->
            // Show/hide loading spinner
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    // Load dashboard data using the keypass from login
    private fun loadDashboard() {
        val keypass = intent.getStringExtra(EXTRA_KEYPASS)
        // Check if keypass is valid
        if (keypass.isNullOrBlank()) {
            binding.errorTextView.apply {
                text = "Invalid keypass"
                visibility = View.VISIBLE
            }
            return
        }
        // Request data from ViewModel
        viewModel.loadDashboard(keypass)
    }
} 