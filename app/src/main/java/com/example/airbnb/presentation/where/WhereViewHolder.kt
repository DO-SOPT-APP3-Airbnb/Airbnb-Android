package com.example.airbnb.presentation.where

import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.R
import com.example.airbnb.databinding.ItemWhereLocationBinding

class WhereViewHolder(val binding: ItemWhereLocationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: WhereItem) {
        binding.locationItem = item

        if (!item.isClicked) {
            binding.tvWhereLocationName.setBackgroundResource(R.drawable.shape_gray_icon_fill_top10_rect)
        }
    }
}