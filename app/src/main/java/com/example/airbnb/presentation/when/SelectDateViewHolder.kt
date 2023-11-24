package com.example.airbnb.presentation.`when`

import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemWhenSelectDateBinding

class SelectDateViewHolder(
    private val binding: ItemWhenSelectDateBinding,
    private val onSelectDateClick: (String, Int) -> Unit = { _, _ -> }
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: String) {
        binding.btnWhenSelectDate.text = "$data 일"
        binding.btnWhenSelectDate.setOnClickListener {
            binding.btnWhenSelectDate.isSelected = !binding.btnWhenSelectDate.isSelected
        }
        onSelectDateClick(data, adapterPosition)
    }
}