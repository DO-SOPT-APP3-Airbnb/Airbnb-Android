package com.example.airbnb.presentation.explore

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingFragment
import com.example.airbnb.core.view.UiState
import com.example.airbnb.data.DummyExploreImageList
import com.example.airbnb.data.ExploreInfoData
import com.example.airbnb.databinding.FragmentExploreBinding
import com.example.airbnb.presentation.where.WhereActivity
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {

    private lateinit var exploreViewPagerAdapter: ExploreViewPagerAdapter
    private val exploreViewModel by viewModels<ExploreViewModel>()


    override fun initView() {
        setViewPager()
        goWhereActivity()
        apiImageUrlObserve()
        apiImageInfoObserve()
    }

    fun setViewPager() {
        val dummyExploreImageList = DummyExploreImageList.dummyExploreInfoData
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
