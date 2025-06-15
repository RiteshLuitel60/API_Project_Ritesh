/**
 * LoginActivity - The main entry point of the application
 * 
 * This activity handles user authentication by:
 * 1. Displaying a login form with username and password fields
 * 2. Validating user input
 * 3. Communicating with the LoginViewModel to perform authentication
 * 4. Handling success/failure states and navigation
 * 5. Managing UI state (loading, error messages)
 */
package com.example.api_project_ritesh.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.api_project_ritesh.databinding.ActivityLoginBinding
import com.example.api_project_ritesh.ui.dashboard.DashboardActivity
import com.example.api_project_ritesh.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @AndroidEntryPoint annotation enables Hilt dependency injection for this activity
 * This allows the LoginViewModel to be automatically injected
 */
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    // View binding instance for accessing UI elements
    private lateinit var binding: ActivityLoginBinding
    
    // ViewModel instance injected by Hilt, using viewModels() delegate for lifecycle awareness
    private val viewModel: LoginViewModel by viewModels()

    /**
     * Called when the activity is first created
     * Sets up the UI and initializes observers and click listeners
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize view binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up observers for ViewModel state changes
        setupObservers()
        // Set up click listeners for UI interactions
        setupClickListeners()
    }

    /**
     * Sets up observers for ViewModel LiveData objects
     * Handles:
     * 1. Login result (success/failure)
     * 2. Loading state
     */
    private fun setupObservers() {
        // Observe login result from ViewModel
        viewModel.loginResult.observe(this) { result ->
            result.fold(
                // Handle successful login
                onSuccess = { response ->
                    // Navigate to DashboardActivity with the keypass
                    startActivity(
                        Intent(this, DashboardActivity::class.java).apply {
                            putExtra(DashboardActivity.EXTRA_KEYPASS, response.keypass)
                        }
                    )
                    // Finish this activity to prevent going back to login screen
                    finish()
                },
                // Handle login failure
                onFailure = { error ->
                    // Display error message to user
                    binding.errorTextView.apply {
                        text = error.message
                        visibility = View.VISIBLE
                    }
                }
            )
        }

        // Observe loading state from ViewModel
        viewModel.isLoading.observe(this) { isLoading ->
            // Show/hide progress bar based on loading state
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            // Disable login button while loading
            binding.loginButton.isEnabled = !isLoading
        }
    }

    /**
     * Sets up click listeners for UI elements
     * Handles:
     * 1. Login button click
     * 2. Input validation
     * 3. Error message visibility
     */
    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            // Get user input
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Validate input
            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Hide any previous error messages
            binding.errorTextView.visibility = View.GONE
            // Trigger login through ViewModel
            viewModel.login(username, password)
        }
    }
} 