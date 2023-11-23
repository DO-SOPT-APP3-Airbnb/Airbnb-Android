package com.example.airbnb.presentation.explore

import android.os.Bundle
import android.view.View
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingFragment
import com.example.airbnb.data.DummyExploreImageList
import com.example.airbnb.databinding.FragmentExploreBinding

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {

    private lateinit var exploreViewPagerAdapter: ExploreViewPagerAdapter

    override fun initView() {
        // 로직 작성
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyExploreImageList = DummyExploreImageList.dummyExploreImage
        exploreViewPagerAdapter = ExploreViewPagerAdapter(dummyExploreImageList)
        binding.vpExplore.adapter = exploreViewPagerAdapter
        binding.indicatorViewpagerImageDots.attachTo(binding.vpExplore)
        binding.vpExplore.offscreenPageLimit = 3
        binding.vpExplore.setPadding(40, 0, 40, 0)
    }
}
