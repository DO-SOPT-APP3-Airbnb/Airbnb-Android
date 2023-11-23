package com.example.airbnb.presentation.explore

import android.content.Intent
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingFragment
import com.example.airbnb.data.DummyExploreImageList
import com.example.airbnb.databinding.FragmentExploreBinding
import com.example.airbnb.presentation.where.WhereActivity

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {

    private lateinit var exploreViewPagerAdapter: ExploreViewPagerAdapter

    override fun initView() {
        // 로직 작성
        val dummyExploreImageList = DummyExploreImageList.dummyExploreImage
        exploreViewPagerAdapter = ExploreViewPagerAdapter(dummyExploreImageList)

        binding.run {
            // 뷰페이저 어댑터 연결
            vpExplore.adapter = exploreViewPagerAdapter
            indicatorViewpagerImageDots.attachTo(binding.vpExplore)

            // 뷰페이저 화면 디자인
            vpExplore.offscreenPageLimit = 3
            vpExplore.setPadding(40, 0, 40, 0)

            // 화면 이동
            ivMainSearchBar.setOnClickListener {
                val intent = Intent(requireContext(), WhereActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}
