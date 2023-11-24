package com.example.airbnb.presentation.`when`

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.airbnb.core.view.ItemDiffCallback
import com.example.airbnb.databinding.ItemWhenSelectDateBinding
import com.example.airbnb.databinding.ItemWhenSelectExactDateBinding
import com.example.airbnb.presentation.`when`.viewholder.SelectExactDateViewHolder

class SelectDateAdapter(
    context: Context, private val onSelectDateClick: (String, Int) -> Unit = { _, _ -> }
) : ListAdapter<String, ViewHolder>(
    SelectDateDiffCallback
) {

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun getItemCount(): Int = currentList.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_EXTRACT_DATE
        } else {
            VIEW_TYPE_DATE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EXTRACT_DATE -> {
                val binding = ItemWhenSelectExactDateBinding.inflate(inflater, parent, false)
                SelectExactDateViewHolder(binding, onSelectDateClick)
            }

            else -> {
                val binding = ItemWhenSelectDateBinding.inflate(inflater, parent, false)
                SelectDateViewHolder(binding, onSelectDateClick)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_DATE) {
            (holder as SelectDateViewHolder).run { onBind(currentList[position - 1]) }
        }else{
            (holder as SelectExactDateViewHolder).run { onBind() }
        }
    }

    companion object {
        private val SelectDateDiffCallback =
            ItemDiffCallback<String>(onItemsTheSame = { old, new -> old == new },
                onContentsTheSame = { old, new -> old == new })

        const val VIEW_TYPE_EXTRACT_DATE = 0
        const val VIEW_TYPE_DATE = 1
    }
}