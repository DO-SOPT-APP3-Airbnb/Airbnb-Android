package com.example.airbnb.presentation.`when`.viewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.R
import com.example.airbnb.databinding.ItemWhenSelectExactDateBinding

class SelectExactDateViewHolder(
    private val binding: ItemWhenSelectExactDateBinding,
    private val onSelectDateClick: (String, Int) -> Unit = { _, _ -> }
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind() {
        binding.btnWhenSelectExactDate.setOnClickListener {
            binding.btnWhenSelectExactDate.isSelected = !binding.btnWhenSelectExactDate.isSelected
        }
    }
}