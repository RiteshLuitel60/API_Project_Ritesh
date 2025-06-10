package com.example.api_project_ritesh.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.api_project_ritesh.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    companion object {
        const val EXTRA_ENTITY_NAME = "extra_entity_name"
        const val EXTRA_ENTITY_ARCHITECT = "extra_entity_architect"
        const val EXTRA_ENTITY_LOCATION = "extra_entity_location"
        const val EXTRA_ENTITY_YEAR = "extra_entity_year"
        const val EXTRA_ENTITY_STYLE = "extra_entity_style"
        const val EXTRA_ENTITY_HEIGHT = "extra_entity_height"
        const val EXTRA_ENTITY_DESCRIPTION = "extra_entity_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayEntityDetails()
    }

    private fun displayEntityDetails() {
        val name = intent.getStringExtra(EXTRA_ENTITY_NAME) ?: ""
        val architect = intent.getStringExtra(EXTRA_ENTITY_ARCHITECT) ?: ""
        val location = intent.getStringExtra(EXTRA_ENTITY_LOCATION) ?: ""
        val year = intent.getStringExtra(EXTRA_ENTITY_YEAR) ?: ""
        val style = intent.getStringExtra(EXTRA_ENTITY_STYLE) ?: ""
        val height = intent.getStringExtra(EXTRA_ENTITY_HEIGHT) ?: ""
        val description = intent.getStringExtra(EXTRA_ENTITY_DESCRIPTION) ?: ""

        binding.apply {
            nameTextView.text = name
            architectTextView.text = "Architect: $architect"
            locationTextView.text = "Location: $location"
            yearTextView.text = "Year Completed: $year"
            styleTextView.text = "Style: $style"
            heightTextView.text = "Height: ${height}m"
            descriptionTextView.text = description
        }
    }
} 