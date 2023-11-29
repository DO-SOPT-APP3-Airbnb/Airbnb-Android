package com.example.airbnb.presentation.who

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.ConcurrentHashMap

class WhoViewModel : ViewModel() {

    private val whoCountMap: MutableMap<String, MutableLiveData<Int>> = ConcurrentHashMap()
    private val types = arrayOf("adult", "kids", "toddler", "pet")

    init {
        types.forEach { initCount(it) }
    }

    private fun initCount(type: String) {
        whoCountMap[type] = MutableLiveData(0)
    }

    fun getCount(type: String): MutableLiveData<Int> {
        return whoCountMap[type] ?: MutableLiveData(0)
    }

    fun increaseCount(type: String) {
        getCount(type).value = getCount(type).value?.plus(1) ?: 0
    }

    fun decreaseCount(type: String) {
        getCount(type).value = maxOf(0, getCount(type).value?.minus(1) ?: 0)
    }
}