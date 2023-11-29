package com.example.airbnb.presentation.where

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.R
import com.example.airbnb.databinding.ItemWhereLocationBinding
import com.example.airbnb.presentation.`when`.WhenActivity

class WherePagerAdapter(private val items: List<WhereItem>) :
    RecyclerView.Adapter<WhereViewHolder>() {

    // 새로운 뷰 홀더 객체를 생성하고 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhereViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWhereLocationBinding.inflate(inflater, parent, false)
        return WhereViewHolder(binding)
    }

    // 각 항목에 해당하는 데이터를 뷰 홀더에 바인딩
    override fun onBindViewHolder(holder: WhereViewHolder, position: Int) {
        val item = items[position]
        holder.onBind(item)

        updateItemBackground(holder, item)
    }

    private fun updateItemBackground(
        holder: WhereViewHolder,
        item: WhereItem
    ) {
        holder.itemView.setOnClickListener {
            if (!item.isClicked) {
                // 배경이 변경되지 않은 경우 배경 변경
                holder.binding.tvWhereLocationName.setBackgroundResource(R.drawable.shape_main1_fill_top10_rect)
                item.isClicked = true
            } else {
                // 배경이 변경된 경우 when 페이지로 이동
                val intent = Intent(holder.itemView.context, WhenActivity::class.java)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    // 어댑터가 관리하는 아이템의 총 개수 반환
    override fun getItemCount(): Int = items.size

    // 어댑터가 관리하는 아이템 리스트 반환
    fun getItems(): List<WhereItem> = items

}