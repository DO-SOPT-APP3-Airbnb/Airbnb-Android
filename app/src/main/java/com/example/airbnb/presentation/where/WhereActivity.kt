package com.example.airbnb.presentation.where

import androidx.activity.viewModels
import android.graphics.Color
import androidx.activity.viewModels
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingActivity
import com.example.airbnb.core.view.UiState
import com.example.airbnb.databinding.ActivityWhereBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class WhereActivity : BindingActivity<ActivityWhereBinding>(R.layout.activity_where) {
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: WherePagerAdapter
    private lateinit var tabLayout: TabLayout

    private val whereViewModel by viewModels<WhereViewModel>()

    override fun initView() {
        binding.appbarWhen.tvAppbarTitleLodging.setTextColor(Color.WHITE)
        binding.appbarWhen.viewAppbarTitleBar.setBackgroundColor(Color.WHITE)
        whereObserve()
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

    private fun whereObserve() {
        whereViewModel.whereLiveData.observe(this) {
            when (it) {
                is UiState.Success -> {
                    Timber.d("성공")

                    // ViewPager 및 어댑터 초기화
                    viewPager = binding.vpWhereLocation
                    adapter = WherePagerAdapter(it.data)

                    initViewPager()
                    initTabLayout()
                }

                is UiState.Failure -> Timber.d("실패")
                is UiState.Loading -> Timber.d("로딩중")
            }
        }

        whereViewModel.getNickName.observe(this){
            when(it){
                is UiState.Success -> binding.tvWhereTitle.text = it.data.toString() + "님,\n이번엔 어디로 여행가시나요?"
                is UiState.Failure -> Timber.d("실패")
                is UiState.Loading -> Timber.d("로딩중")
            }
        }
    }
}
