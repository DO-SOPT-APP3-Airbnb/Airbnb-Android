package com.example.airbnb.presentation.`when`

import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.ItemCalenderDateBinding

class CalenderViewHolder(
    private val binding: ItemCalenderDateBinding,
    private val onCalenderDateClick: (String, Int) -> Unit = { _, _ -> }
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: String) {
        binding.tvCalenderDate.text = data
    }
}
