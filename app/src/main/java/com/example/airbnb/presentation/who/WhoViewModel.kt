package com.example.airbnb.presentation.who

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Integer.max

class WhoViewModel : ViewModel() {

    private val whoCountMap: MutableMap<String, MutableLiveData<Int>> = HashMap()

    init {
        whoCountMap["adult"] = MutableLiveData(0)
        whoCountMap["kids"] = MutableLiveData(0)
        whoCountMap["toddler"] = MutableLiveData(0)
        whoCountMap["pet"] = MutableLiveData(0)
    }

    fun getCount(type: String): MutableLiveData<Int> {
        return whoCountMap[type] ?: MutableLiveData(0)
    }

    fun increaseCount(type: String) {
        getCount(type).value = getCount(type).value?.plus(1) ?: 0
    }

    fun decreaseCount(type: String) {
        getCount(type).value = max(0, getCount(type).value?.minus(1) ?: 0)
    }
}