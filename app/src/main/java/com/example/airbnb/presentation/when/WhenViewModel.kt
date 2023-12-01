package com.example.airbnb.presentation.`when`

import androidx.lifecycle.ViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class WhenViewModel : ViewModel() {

    private val currentDate = LocalDate.now()

    val selectDateButtonList = listOf("1", "2", "3", "4", "5", "6", "7")

    fun getYearMonth(): String {
        val formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.getDefault())
        return currentDate.format(formatter)
    }

    fun getCalendarDateList(): List<String> {
        val firstDayOfMonth = currentDate.withDayOfMonth(1)
        val dayOfWeekValue = firstDayOfMonth.dayOfWeek.value

        val calendarDateList = mutableListOf<String>()

        addEmptyDaysToList(calendarDateList, dayOfWeekValue)
        addDaysOfMonthToList(calendarDateList, firstDayOfMonth)

        return calendarDateList
    }

    private fun addEmptyDaysToList(list: MutableList<String>, dayOfWeekValue: Int) {
        //현재 달의 1일이 요일에 따라 빈 값을 추가한 후 숫자를 리스트에 넣기, 단 1일이 일요일이면 넣지 않는다.
        if (dayOfWeekValue != DayOfWeek.SUNDAY.value) {
            for (i in 1 until dayOfWeekValue + 1) {
                list.add("")
            }
        }
    }

    private fun addDaysOfMonthToList(list: MutableList<String>, firstDayOfMonth: LocalDate) {
        //나머지 일 채우기
        val daysInMonth = firstDayOfMonth.lengthOfMonth()
        for (i in 1..daysInMonth) {
            list.add(i.toString())
        }
    }

    fun getTodayPosition(): Int {
        // 오늘 날짜가 리스트에 있는지 확인하고 있다면 그 위치 반환
        for ((index, value) in getCalendarDateList().withIndex()) {
            if (value == currentDate.dayOfMonth.toString()) {
                return index
            }
        }
        return -1 //예외 처리
    }

    companion object {
        const val DATE_TIME_FORMAT = "yyyy MM월"
    }
}