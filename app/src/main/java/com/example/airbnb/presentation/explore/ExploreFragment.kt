package com.example.airbnb.presentation.explore

import android.content.Intent
import androidx.fragment.app.viewModels
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingFragment
import com.example.airbnb.core.util.fragment.toast
import com.example.airbnb.core.view.UiState
import com.example.airbnb.databinding.FragmentExploreBinding
import com.example.airbnb.presentation.where.WhereActivity
import com.google.android.material.tabs.TabLayout
import timber.log.Timber

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {

    private lateinit var exploreViewPagerAdapter: ExploreViewPagerAdapter
    private val exploreViewModel by viewModels<ExploreViewModel>()

    override fun initView() {
        // 뷰페이저 초기화
        setViewPager(0)
        goWhereActivity()
        apiImageUrlObserve()
        selectTab()
    }

    private fun selectTab() {
        binding.tapNavMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                setViewPager(tab?.position ?: 0)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                toast("이미 선택된 탭입니다.")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setViewPager(tabId: Int) {
        binding.run {
            // 뷰페이저 화면 디자인
            vpExplore.offscreenPageLimit = 3
            vpExplore.setPadding(40, 0, 40, 0)

            exploreViewModel.getImageAndInfo(tabId + 1, tabId + 1)
        }
    }

    // 뷰모델 라이브 데이터 값 가져오기 (info, url 합친 리스트)
    private fun apiImageUrlObserve() {
        exploreViewModel.exploreImageLiveData.observe(this) {
            when (it) {
                is UiState.Success -> {
                    val dummyExploreImageList = it.data
                    exploreViewPagerAdapter = ExploreViewPagerAdapter(dummyExploreImageList)
                    binding.vpExplore.adapter = exploreViewPagerAdapter
                }

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
