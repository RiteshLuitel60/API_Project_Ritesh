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

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.loginResult.observe(this) { result ->
            result.fold(
                onSuccess = { response ->
                    startActivity(
                        Intent(this, DashboardActivity::class.java).apply {
                            putExtra(DashboardActivity.EXTRA_KEYPASS, response.keypass)
                        }
                    )
                    finish()
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
            binding.loginButton.isEnabled = !isLoading
        }
    }

    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.errorTextView.visibility = View.GONE
            viewModel.login(username, password)
        }
    }
} 