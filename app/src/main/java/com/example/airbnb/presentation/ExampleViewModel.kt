package com.example.airbnb.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.core.util.enqueueUtil
import com.example.airbnb.core.view.UiState
import com.example.airbnb.data.ServicePool
import com.example.airbnb.data.dto.response.ResponseExampleDto
import kotlinx.coroutines.launch

class ExampleViewModel : ViewModel() {
    private val _exampleLiveData: MutableLiveData<UiState<ResponseExampleDto>> = MutableLiveData()
    val exampleLiveData: MutableLiveData<UiState<ResponseExampleDto>> = _exampleLiveData

    init {
        postExample1()
        postExample2()
    }

    //기본
    private fun postExample1() {
        _exampleLiveData.value = UiState.Loading
        ServicePool.exampleService.postExample1(Unit)
            .enqueueUtil(onSuccess = { _exampleLiveData.value = UiState.Success(it) },
                onError = { _exampleLiveData.value = UiState.Failure(it.toString()) })
    }

    //코루틴 사용시
    private fun postExample2() = viewModelScope.launch {
        _exampleLiveData.value = UiState.Loading
        runCatching {
            ServicePool.exampleService.postExample2(Unit)
        }.fold({ _exampleLiveData.value = UiState.Success(it) },
            { _exampleLiveData.value = UiState.Failure(it.message.toString()) })
    }
}