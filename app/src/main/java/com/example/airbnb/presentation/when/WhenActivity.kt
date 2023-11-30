package com.example.airbnb.presentation.`when`

import android.content.Intent
import com.example.airbnb.R
import com.example.airbnb.core.base.BindingActivity
import com.example.airbnb.databinding.ActivityWhenBinding
import com.example.airbnb.presentation.who.WhoActivity
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class WhenActivity : BindingActivity<ActivityWhenBinding>(R.layout.activity_when) {

    override fun initView() {
        initCalenderAdapter()
        initSelectDateAdapter()
        initWhenClickListener()
        getFormattedDate()
    }

    private fun initWhenClickListener() {
        with(binding) {
            tvWhenNavigationSkip.setOnClickListener { navigateToSignInWho() }
            btnWhenNavigationNext.setOnClickListener { navigateToSignInWho() }
        }
    }

    private fun initCalenderAdapter() {
        binding.rvWhenCalenderDate.adapter =
            CalenderAdapter(this, findTodayPosition(createCustomCalendar())).apply {
                submitList(createCustomCalendar())
            }
    }

    private fun initSelectDateAdapter() {
        binding.rvWhenSelectDate.adapter = SelectDateAdapter(this).apply {
            submitList(listOf("1", "2", "3", "4", "5", "6", "7"))
        }
    }

    private fun createCustomCalendar(): List<String> {
        val currentDate = LocalDate.now()
        val firstDayOfMonth = currentDate.withDayOfMonth(1)
        val dayOfWeekValue = firstDayOfMonth.dayOfWeek.value
        val startIndex = if (dayOfWeekValue != DayOfWeek.SUNDAY.value) dayOfWeekValue else 0

        val calendarList = mutableListOf<String>()

        //현재 달의 1일이 요일에 따라 빈 값을 추가한 후 숫자를 리스트에 넣기, 단 1일이 일요일이면 넣지 않는다.
        if (dayOfWeekValue != DayOfWeek.SUNDAY.value) {
            for (i in 1 until dayOfWeekValue + 1) {
                calendarList.add("")
            }
        }

        //나머지 일 채우기
        val daysInMonth = firstDayOfMonth.lengthOfMonth()
        for (i in 1..daysInMonth) {
            calendarList.add(i.toString())
        }

        return calendarList
    }

    private fun navigateToSignInWho() {
        Intent(this, WhoActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(this)
            finish()
        }
    }

    private fun findTodayPosition(calendarList: List<String>): Int {
        val currentDate = LocalDate.now()
        val firstDayOfMonth = currentDate.withDayOfMonth(1)
        val dayOfWeekValue = firstDayOfMonth.dayOfWeek.value
        val startIndex = if (dayOfWeekValue != DayOfWeek.SUNDAY.value) dayOfWeekValue else 0

        // 오늘 날짜가 리스트에 있는지 확인하고 있다면 그 위치 반환
        for ((index, value) in calendarList.withIndex()) {
            if (value == currentDate.dayOfMonth.toString()) {
                return index
            }
        }

        return -1
    }

    private fun getFormattedDate() {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy MM월", Locale.getDefault())
        binding.tvWhenCalenderYearMonth.text = currentDate.format(formatter
        )
    }
}

