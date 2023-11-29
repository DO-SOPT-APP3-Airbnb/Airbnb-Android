package com.example.airbnb.presentation.explore

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingFragment
import com.example.airbnb.core.view.UiState
import com.example.airbnb.data.ExploreInfoData
import com.example.airbnb.databinding.FragmentExploreBinding
import com.example.airbnb.presentation.where.WhereActivity
import com.google.android.material.tabs.TabLayoutMediator

class ExploreFragment : BindingFragment<FragmentExploreBinding>(R.layout.fragment_explore) {

    private lateinit var exploreViewPagerAdapter: ExploreViewPagerAdapter
    private val exploreViewModel by viewModels<ExploreViewModel>()
    private val exploreImageTotal: MutableList<String> = mutableListOf()
    private var exploreInfoTotal = ExploreInfoData(
        Image = "null",
        description = "null",
        distance = 0,
        travelDate = "null",
        price = 0,
        score = 0.0,
    )

    override fun initView() {
        setViewPager()
        goWhereActivity()
    }

    fun setViewPager() {
        val exploreTotalList: MutableList<ExploreInfoData> = mutableListOf()

        // 4번 api 호출하기
        for (i in 0..3) {
            getApiImageUrl(i + 1)
            getApiImageInfo(i + 1)
            exploreTotalList.add(exploreInfoTotal)

            Log.d("TAG", "setViewPager: $exploreTotalList")
        }

        exploreViewPagerAdapter = ExploreViewPagerAdapter(exploreTotalList)

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

    fun getApiImageUrl(tabId: Int) {
        exploreViewModel.exploreImageLiveData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    // 로딩
                }

                is UiState.Success -> {
                    uiState.data.run {
                        // 첫 .data는 uiState의 data, 두번째 .data는 response의 data
                        // imageUrlList.add(data?.imageUrl ?: "")
                        exploreImageTotal.add(data?.imageUrl ?: "null")
                        Log.d("TAG", "getApiImageUrl: $exploreImageTotal")
                    }
                }

                is UiState.Failure -> {
                    // 실패
                }
            }
        }
        exploreViewModel.getImage(tabId)
    }

    fun getApiImageInfo(tabId: Int) {
        exploreViewModel.exploreInfoLiveData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    // 로딩
                }

                is UiState.Success -> {
                    uiState.data.run {
                        // 첫 .data는 uiState의 data, 두번째 .data는 response의 data
                        exploreInfoTotal = data?.let {
                            ExploreInfoData(
                                Image = exploreImageTotal[tabId],
                                description = it.description,
                                distance = it.distance,
                                travelDate = it.travelDate,
                                price = it.price,
                                score = it.scope,
                            )
                        } ?: run {
                            ExploreInfoData(
                                Image = "null",
                                description = "null",
                                distance = 0,
                                travelDate = "null",
                                price = 0,
                                score = 0.0,
                            )
                        }
                    }
                }

                is UiState.Failure -> {
                    // 실패
                }
            }
        }
        exploreViewModel.getInfo(tabId)
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
