package com.example.airbnb.presentation.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.airbnb.R
import com.example.airbnb.data.ExploreInfoData
import com.example.airbnb.databinding.ItemExploreViewpageBinding

class ExploreViewPagerAdapter(private val viewPagerTotalList: List<ExploreInfoData>) :
    RecyclerView.Adapter<ExploreViewPagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_explore_viewpage, parent, false)
        return PagerViewHolder(ItemExploreViewpageBinding.bind(view))
    }

    class PagerViewHolder(private val binding: ItemExploreViewpageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(imageInfo: ExploreInfoData) {
            binding.run {
                ivViewPagerImage.load(imageInfo.Image) {
                    placeholder(R.drawable.shape_gray_fill_14_rect_image_loading)
                    error(R.drawable.shape_gray_fill_14_rect_image_loading)
                }

                // 모서리 둥글
                ivViewPagerImage.clipToOutline = true

                favoriteSelected()

                tvViewPagerTitle.text = imageInfo.description
                "${imageInfo.distance}km 거리".also { tvViewPagerLocation.text = it }
                tvViewPagerDate.text = imageInfo.travelDate
                "₩${imageInfo.price} /박".also { tvViewPagerPrice.text = it }

                tvViewPagerScore.text = imageInfo.scope.toString()
            }
        }

        // 좋아요 버튼 클릭 기능
        private fun ItemExploreViewpageBinding.favoriteSelected() {
            var isFavorite = ibViewPagerFavorite.isSelected
            ibViewPagerFavorite.setOnClickListener {
                isFavorite = !isFavorite
                if (isFavorite) {
                    ibViewPagerFavorite.setImageResource(R.drawable.ic_explore_favorite_selected)
                } else {
                    ibViewPagerFavorite.setImageResource(R.drawable.ic_explore_favorite_default)
                }
            }
        }
    }

    override fun getItemCount(): Int = viewPagerTotalList.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val exploreImage = viewPagerTotalList[position]
        holder.onBindView(exploreImage)
    }
}
