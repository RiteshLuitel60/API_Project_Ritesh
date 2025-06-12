// RecyclerView adapter for displaying architectural landmarks in a list
package com.example.api_project_ritesh.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api_project_ritesh.data.model.Entity
import com.example.api_project_ritesh.databinding.ItemEntityBinding

class EntityAdapter(
    private val onItemClick: (Entity) -> Unit
) : ListAdapter<Entity, EntityAdapter.EntityViewHolder>(EntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val binding = ItemEntityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EntityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EntityViewHolder(
        private val binding: ItemEntityBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(entity: Entity) {
            // Get the first two entries from the map
            val summary = entity.entries.take(2).map { it.key to it.value.toString() }
            
            // Set the name (first field) as the title
            binding.property1TextView.text = summary.firstOrNull()?.second ?: ""
            
            // Set the second field as the subtitle
            binding.property2TextView.text = summary.getOrNull(1)?.let { (key, value) ->
                val formattedKey = key.replace(Regex("([A-Z])"), " $1")
                    .trim()
                    .replaceFirstChar { it.uppercase() }
                "$formattedKey: $value"
            } ?: ""
        }
    }

    private class EntityDiffCallback : DiffUtil.ItemCallback<Entity>() {
        override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            // Compare the first field's value as the unique identifier
            return oldItem.entries.firstOrNull()?.value == newItem.entries.firstOrNull()?.value
        }

        override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem == newItem
        }
    }
} 