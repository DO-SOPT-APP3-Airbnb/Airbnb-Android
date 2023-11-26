package com.example.airbnb.presentation.`when`

import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemWhenSelectDateBinding

class SelectDateViewHolder(
    private val binding: ItemWhenSelectDateBinding,
    private val onItemSelected: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnWhenSelectDate.setOnClickListener {
            val currentPosition = adapterPosition
            onItemSelected(currentPosition)
        }
    }

    fun onBind(data: String, isSelected: Boolean) {
        binding.btnWhenSelectDate.text = "$data 일"
        binding.btnWhenSelectDate.isSelected = isSelected
    }
}