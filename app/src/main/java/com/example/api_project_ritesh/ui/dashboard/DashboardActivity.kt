package com.example.api_project_ritesh.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_project_ritesh.databinding.ActivityDashboardBinding
import com.example.api_project_ritesh.ui.details.DetailsActivity
import com.example.api_project_ritesh.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: EntityAdapter

    companion object {
        const val EXTRA_KEYPASS = "extra_keypass"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        loadDashboard()
    }

    private fun setupRecyclerView() {
        adapter = EntityAdapter { entity ->
            viewModel.selectEntity(entity)
            startActivity(
                Intent(this, DetailsActivity::class.java).apply {
                    putExtra(DetailsActivity.EXTRA_ENTITY_NAME, entity.name)
                    putExtra(DetailsActivity.EXTRA_ENTITY_ARCHITECT, entity.architect)
                    putExtra(DetailsActivity.EXTRA_ENTITY_LOCATION, entity.location)
                    putExtra(DetailsActivity.EXTRA_ENTITY_YEAR, entity.yearCompleted.toString())
                    putExtra(DetailsActivity.EXTRA_ENTITY_STYLE, entity.style)
                    putExtra(DetailsActivity.EXTRA_ENTITY_HEIGHT, entity.height.toString())
                    putExtra(DetailsActivity.EXTRA_ENTITY_DESCRIPTION, entity.description)
                }
            )
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = this@DashboardActivity.adapter
        }
    }

    private fun setupObservers() {
        viewModel.dashboardData.observe(this) { result ->
            result.fold(
                onSuccess = { response ->
                    adapter.submitList(response.entities)
                    binding.errorTextView.visibility = View.GONE
                },
                onFailure = { error ->
                    binding.errorTextView.apply {
                        text = error.message
                        visibility = View.VISIBLE
                    }
                }
            )
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun loadDashboard() {
        val keypass = intent.getStringExtra(EXTRA_KEYPASS)
        if (keypass.isNullOrBlank()) {
            binding.errorTextView.apply {
                text = "Invalid keypass"
                visibility = View.VISIBLE
            }
            return
        }
        viewModel.loadDashboard(keypass)
    }
} 