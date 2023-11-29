package com.example.airbnb.presentation.`when`

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.airbnb.core.view.ItemDiffCallback
import com.example.airbnb.databinding.ItemCalenderDateBinding
import com.example.airbnb.presentation.`when`.viewholder.CalenderViewHolder

class CalenderAdapter(
    private val context: Context
) :
    ListAdapter<String, CalenderViewHolder>(
        ArticleAllDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
        val binding =
            ItemCalenderDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalenderViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        holder.onBind(position, getItem(position))
    }

    companion object {
        private val ArticleAllDiffCallback =
            ItemDiffCallback<String>(onItemsTheSame = { old, new -> old == new },
                onContentsTheSame = { old, new -> old == new })
    }
}