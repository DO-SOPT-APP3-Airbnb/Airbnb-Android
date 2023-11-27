package com.example.airbnb.presentation.`when`.viewholder

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.R
import com.example.airbnb.databinding.ItemCalenderDateBinding

class CalenderViewHolder(
    private val context: Context,
    private val binding: ItemCalenderDateBinding,
    private val onCalenderDateClick: (String, Int) -> Unit = { _, _ -> }
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(position: Int, data: String) {
        if (position % 7 == 0) {
            binding.tvCalenderDate.setTextColor(
                ContextCompat.getColor(
                    context, R.color.main1
                )
            )
        }
        binding.tvCalenderDate.text = data
    }
}
