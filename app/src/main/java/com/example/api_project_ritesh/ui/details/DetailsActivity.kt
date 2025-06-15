// Details screen activity - displays comprehensive information about selected Item
package com.example.api_project_ritesh.ui.details

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.api_project_ritesh.R
import com.example.api_project_ritesh.databinding.ActivityDetailsBinding
import com.example.api_project_ritesh.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    companion object {
        const val EXTRA_ENTITY = "extra_entity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up toolbar back button
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set up logout button
        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        @Suppress("UNCHECKED_CAST")
        val entity = intent.getSerializableExtra(EXTRA_ENTITY) as? HashMap<String, Any?>
        if (entity != null) {
            displayEntityDetails(entity)
        } else {
            finish()
        }
    }

    private fun displayEntityDetails(entity: Map<String, Any?>) {
        binding.apply {
            // Clear any existing views
            detailsContainer.removeAllViews()
            
            // Add all fields from the entity
            entity.forEach { (key, value) ->
                val formattedKey = key.replace(Regex("([A-Z])"), " $1")
                    .trim()
                    .replaceFirstChar { it.uppercase() }
                
                val formattedValue = when (value) {
                    null -> "N/A"
                    is List<*> -> value.joinToString(", ")
                    is Map<*, *> -> value.entries.joinToString("\n") { (k, v) -> "$k: $v" }
                    else -> value.toString()
                }
                
                addDetailField(formattedKey, formattedValue)
            }
        }
    }

    private fun ActivityDetailsBinding.addDetailField(label: String, value: String) {
        val fieldView = layoutInflater.inflate(R.layout.item_detail_field, detailsContainer, false)
        fieldView.findViewById<TextView>(R.id.fieldLabel).text = label
        fieldView.findViewById<TextView>(R.id.fieldValue).text = value
        detailsContainer.addView(fieldView)
    }
} 