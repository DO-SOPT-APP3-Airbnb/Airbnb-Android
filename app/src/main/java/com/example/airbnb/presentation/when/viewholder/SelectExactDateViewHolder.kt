package com.example.airbnb.presentation.`when`.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemWhenSelectExactDateBinding

class SelectExactDateViewHolder(
    private val binding: ItemWhenSelectExactDateBinding,
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnWhenSelectExactDate.setOnClickListener {
            val currentPosition = adapterPosition
            onItemSelected(currentPosition)

        }
    }

    fun onBind(isSelected: Boolean) {
        binding.btnWhenSelectExactDate.isSelected = isSelected
    }
}