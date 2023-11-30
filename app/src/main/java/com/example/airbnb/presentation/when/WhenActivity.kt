package com.example.airbnb.presentation.`when`

import android.content.Intent
import androidx.activity.viewModels
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingActivity
import com.example.airbnb.databinding.ActivityWhenBinding
import com.example.airbnb.presentation.who.WhoActivity

class WhenActivity : BindingActivity<ActivityWhenBinding>(R.layout.activity_when) {

    private val whenViewModel by viewModels<WhenViewModel>()

    override fun initView() {
        setTodayYearMonthText()
        initCalenderAdapter()
        initSelectDateButtonAdapter()
        initWhenClickListener()
    }

    private fun setTodayYearMonthText() {
        binding.tvWhenCalenderYearMonth.text = whenViewModel.getYearMonth()
    }

    private fun initCalenderAdapter() {
        binding.rvWhenCalenderDate.adapter =
            CalenderAdapter(this, whenViewModel.getTodayPosition()).apply {
                submitList(whenViewModel.getCalendarDateList())
            }
    }

    private fun initSelectDateButtonAdapter() {
        binding.rvWhenSelectDate.adapter = SelectDateAdapter(this).apply {
            submitList(whenViewModel.selectDateButtonList)
        }
    }

    private fun initWhenClickListener() {
        with(binding) {
            tvWhenNavigationSkip.setOnClickListener { navigateToSignInWho() }
            btnWhenNavigationNext.setOnClickListener { navigateToSignInWho() }
        }
    }

    private fun navigateToSignInWho() {
        Intent(this, WhoActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(this)
            finish()
        }
    }
}

