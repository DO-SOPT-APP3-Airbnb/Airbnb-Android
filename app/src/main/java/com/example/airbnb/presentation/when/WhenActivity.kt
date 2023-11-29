package com.example.airbnb.presentation.`when`

import com.example.airbnb.R
import com.example.airbnb.core.base.BindingActivity
import com.example.airbnb.databinding.ActivityWhenBinding
import java.time.DayOfWeek
import java.time.LocalDate

class WhenActivity : BindingActivity<ActivityWhenBinding>(R.layout.activity_when) {

    override fun initView() {
        initCalenderAdapter()
        initSelectDateAdapter()
    }

    private fun initCalenderAdapter() {
        binding.rvWhenCalenderDate.adapter =
            CalenderAdapter(this).apply {
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
}

