package com.example.airbnb.presentation.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingFragment
import com.example.airbnb.databinding.FragmentExploreBinding

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {

    lateinit var viewpagerImage: ViewPager2

    override fun initView() {
        // 로직 작성
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_explore, container, false)

        viewpagerImage = rootView.findViewById(R.id.vp_explore)
        viewpagerImage.adapter = ExploreViewPagerAdapter(getViewPagerImageList())
        viewpagerImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        return rootView
    }

    // 뷰 페이저에 들어갈 아이템
    private fun getViewPagerImageList(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.ex_good1,
            R.drawable.ex_good2,
            R.drawable.ex_room1,
            R.drawable.ex_room2,
        )
    }
}
