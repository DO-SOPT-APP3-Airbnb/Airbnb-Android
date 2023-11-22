package com.example.airbnb.presentation.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.airbnb.R
import com.example.airbnb.data.ExploreImage
import com.example.airbnb.databinding.ItemExploreViewpageBinding

class ExploreViewPagerAdapter(private val imageList: List<ExploreImage>) :
    RecyclerView.Adapter<ExploreViewPagerAdapter.PagerViewHolder>() {

    // private var imageList: List<ExploreImage> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_explore_viewpage, parent, false)
        return PagerViewHolder(ItemExploreViewpageBinding.bind(view))
    }

    class PagerViewHolder(private val binding: ItemExploreViewpageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(imageInfo: ExploreImage) {
            binding.run {
                ivViewPagerImage.load(imageInfo.Image) {
                    placeholder(R.drawable.shape_gray_fill_14_rect_image_loading)
                    error(R.drawable.shape_gray_fill_14_rect_image_loading)
                }

                // 모서리 둥글
                ivViewPagerImage.clipToOutline = true

                tvViewPagerTitle.text = imageInfo.title
                tvViewPagerLocation.text = imageInfo.location
                tvViewPagerDate.text = imageInfo.date
                tvViewPagerPrice.text = imageInfo.price
                tvViewPagerScore.text = imageInfo.score

                if (imageInfo.favorite) {
                    ibViewPagerFavorite.setImageResource(R.drawable.ic_explore_favorite_selected)
                }
            }
        }
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val exploreImage = imageList[position]
        holder.onBindView(exploreImage)
    }
}
