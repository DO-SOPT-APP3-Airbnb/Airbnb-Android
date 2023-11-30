package com.example.airbnb.presentation.`when`.viewholder

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.R
import com.example.airbnb.core.util.context.colorOf
import com.example.airbnb.databinding.ItemCalenderDateBinding

class CalenderViewHolder(
    private val context: Context,
    private val todayPosition: Int,
    private val binding: ItemCalenderDateBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(position: Int, data: String) {
        if (position % 7 == 0) {
            binding.tvCalenderDate.setTextColor(context.colorOf(R.color.main1))
        }
        if (position < todayPosition && data.isNotEmpty()) {
            binding.tvCalenderDate.setTextColor(context.colorOf(R.color.gray_text))
            binding.tvCalenderDate.paintFlags = binding.tvCalenderDate.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            binding.tvCalenderDate.setOnClickListener {
                binding.tvCalenderDate.isSelected = !binding.tvCalenderDate.isSelected
            }
        }
        binding.tvCalenderDate.text = data
    }
}
