package com.example.airbnb.presentation.explore

import android.content.Intent
import androidx.fragment.app.viewModels
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingFragment
import com.example.airbnb.core.view.UiState
import com.example.airbnb.data.DummyExploreImageList
import com.example.airbnb.databinding.FragmentExploreBinding
import com.example.airbnb.presentation.where.WhereActivity
import com.google.android.material.tabs.TabLayout
import timber.log.Timber

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {

    private lateinit var exploreViewPagerAdapter: ExploreViewPagerAdapter
    private val exploreViewModel by viewModels<ExploreViewModel>()

    override fun initView() {
        setViewPager(0)
        goWhereActivity()
        apiImageUrlObserve()
        apiImageInfoObserve()
        selectTab()
    }

    private fun selectTab() {
        binding.tapNavMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
                setViewPager(tab?.position ?: 0)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }

    private fun setViewPager(tabId: Int = 0) {
        val dummyExploreImageList = DummyExploreImageList.dummyExploreInfoData[tabId]
        exploreViewPagerAdapter = ExploreViewPagerAdapter(dummyExploreImageList)

        binding.run {
            // 뷰페이저 어댑터 연결
            vpExplore.adapter = exploreViewPagerAdapter
            indicatorViewpagerImageDots.attachTo(binding.vpExplore)

            // 뷰페이저 화면 디자인
            vpExplore.offscreenPageLimit = 3
            vpExplore.setPadding(40, 0, 40, 0)

            exploreViewModel.getImageAndInfo(tabId + 1, tabId + 1)
        }
    }

    // api 확인 함수
    private fun apiImageUrlObserve() {
        exploreViewModel.exploreImageLiveData.observe(this) {
            when (it) {
                is UiState.Success -> Timber.d("성공")
                is UiState.Failure -> Timber.d("실패")
                is UiState.Loading -> Timber.d("로딩중")
            }
        }
    }

    private fun apiImageInfoObserve() {
        exploreViewModel.exploreInfoLiveData.observe(this) {
            when (it) {
                is UiState.Success -> Timber.d("성공")
                is UiState.Failure -> Timber.d("실패")
                is UiState.Loading -> Timber.d("로딩중")
            }
        }
    }

    private fun goWhereActivity() {
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
