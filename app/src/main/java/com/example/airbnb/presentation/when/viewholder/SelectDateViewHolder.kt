package com.example.airbnb.presentation.`when`.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemWhenSelectDateBinding

class SelectDateViewHolder(
    private val binding: ItemWhenSelectDateBinding,
    private val onItemSelected: (Int) -> Unit,
    private val onSelectDateClick: (Int) -> Unit = { _ -> }
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnWhenSelectDate.setOnClickListener {
            onItemSelected(adapterPosition)
            onSelectDateClick(adapterPosition)
        }
    }

    fun onBind(data: String, isSelected: Boolean) {
        binding.btnWhenSelectDate.text = "$data 일"
        binding.btnWhenSelectDate.isSelected = isSelected
    }
}