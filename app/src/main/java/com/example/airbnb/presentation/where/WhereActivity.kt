package com.example.airbnb.presentation.where

import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingActivity
import com.example.airbnb.databinding.ActivityWhereBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WhereActivity : BindingActivity<ActivityWhereBinding>(R.layout.activity_where) {
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: WherePagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun initView() {
        // ViewPager 및 어댑터 초기화
        viewPager = binding.vpWhereLocation
        adapter = WherePagerAdapter(getLocations())  // 서버 API 데이터로 교체해야 함!

        initViewPager()
        initTabLayout()
    }

    private fun initTabLayout() {
        // TabLayout 초기화
        tabLayout = binding.tabWhereLocation
        // ViewPager와 TabLayout 연결
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
    }

    private fun initViewPager() {
        // ViewPager에 어댑터 설정
        viewPager.adapter = adapter

        // ViewPager 다음 페이지 미리 로드
        viewPager.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 1
        }

        // ViewPager간 간격 조정
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(
            MarginPageTransformer(
                resources.getDimensionPixelOffset(
                    R.dimen.page_margin
                )
            )
        )
        viewPager.setPageTransformer(compositePageTransformer)
    }

    override fun onResume() {
        super.onResume()

        // WhenActivity에서 돌아왔을 때, 모든 WhenItem의 isClicked 값을 초기화
        initClickStates()
    }

    private fun initClickStates() {
        adapter.getItems().forEach { it.isClicked = false }
        adapter.notifyDataSetChanged()
    }

    private fun getLocations(): List<WhereItem> {
        return listOf(
            WhereItem("전 세계 어디든 좋아요."),
            WhereItem("유럽"),
            WhereItem("일본")
        )
    }
}