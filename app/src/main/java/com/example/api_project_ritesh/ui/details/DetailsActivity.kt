package com.example.api_project_ritesh.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.api_project_ritesh.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    companion object {
        const val EXTRA_ENTITY_PROPERTY1 = "extra_entity_property1"
        const val EXTRA_ENTITY_PROPERTY2 = "extra_entity_property2"
        const val EXTRA_ENTITY_DESCRIPTION = "extra_entity_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayEntityDetails()
    }

    private fun displayEntityDetails() {
        val property1 = intent.getStringExtra(EXTRA_ENTITY_PROPERTY1) ?: ""
        val property2 = intent.getStringExtra(EXTRA_ENTITY_PROPERTY2) ?: ""
        val description = intent.getStringExtra(EXTRA_ENTITY_DESCRIPTION) ?: ""

        binding.apply {
            property1TextView.text = property1
            property2TextView.text = property2
            descriptionTextView.text = description
        }
    }
} 