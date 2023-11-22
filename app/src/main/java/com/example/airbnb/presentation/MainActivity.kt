package com.example.airbnb.presentation

import androidx.activity.viewModels
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingActivity
import com.example.airbnb.core.view.UiState
import com.example.airbnb.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val exampleViewModel by viewModels<ExampleViewModel>()

    override fun initView() {
        //로직 작성
       // exampleObserve()
    }

    private fun exampleObserve() {
        exampleViewModel.exampleLiveData.observe(this) {
            when (it) {
                is UiState.Success -> Timber.d("성공")
                is UiState.Failure -> Timber.d("실패")
                is UiState.Loading -> Timber.d("로딩중")
            }
        }
    }
}