package com.example.airbnb.presentation.explore

import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingFragment
import com.example.airbnb.data.DummyExploreImageList
import com.example.airbnb.databinding.FragmentExploreBinding
import com.example.airbnb.presentation.where.WhereActivity
import com.google.android.material.tabs.TabLayoutMediator

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {

    private lateinit var exploreViewPagerAdapter: ExploreViewPagerAdapter

    override fun initView() {
        setViewPager()
        goWhereActivity()
    }

    fun setViewPager() {
        val dummyExploreImageList = DummyExploreImageList.dummyExploreImageData
        exploreViewPagerAdapter = ExploreViewPagerAdapter(dummyExploreImageList)

        binding.run {
            // 뷰페이저 어댑터 연결
            vpExplore.adapter = exploreViewPagerAdapter
            indicatorViewpagerImageDots.attachTo(binding.vpExplore)

            // 뷰페이저 화면 디자인
            vpExplore.offscreenPageLimit = 3
            vpExplore.setPadding(40, 0, 40, 0)

            val tabTitles = listOf("통나무집", "방", "최고의 전망", "해변 근처")
            val tabImage = listOf(
                R.drawable.ic_explore_treehouse,
                R.drawable.ic_explore_room,
                R.drawable.ic_explore_goodview,
                R.drawable.ic_explore_seashore,
            )

            // tab, 뷰페이저 연결
            TabLayoutMediator(tapNavMain, vpExplore) { tab, position ->
                tab.text = tabTitles[position]
                tab.icon = ContextCompat.getDrawable(requireContext(), tabImage[position])
            }.attach()
        }
    }

    fun goWhereActivity() {
        binding.run {
            // 화면 이동
            ivMainSearchBar.setOnClickListener {
                val intent = Intent(requireContext(), WhereActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}
