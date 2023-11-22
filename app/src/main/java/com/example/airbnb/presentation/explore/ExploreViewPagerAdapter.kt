package com.example.airbnb.presentation.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.R

class ExploreViewPagerAdapter(var ImageList: ArrayList<Int>) :
    RecyclerView.Adapter<ExploreViewPagerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item_explore_viewpage, parent, false)) {
        val ImageList = itemView.findViewById<ImageView>(R.id.item_viewPager_Image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun getItemCount(): Int = ImageList.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.ImageList.setImageResource(ImageList[position])
    }
}
