package com.example.airbnb.presentation.where

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.airbnb.R
import com.example.airbnb.databinding.ItemWhereLocationBinding

class WhereViewHolder(val binding: ItemWhereLocationBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val context: Context = binding.root.context

    fun onBind(item: WhereItem) {
        binding.locationItem = item
        binding.executePendingBindings()
        Glide.with(context).load(item.locationImageUrl).into(binding.ivWhereLocation)
        binding.ivWhereLocation.clipToOutline = true
        if (!item.isClicked) {
            binding.tvWhereLocationName.setBackgroundResource(R.drawable.shape_gray_icon_fill_top10_rect)
        }
    }
}