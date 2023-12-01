package com.example.airbnb.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.core.view.UiState
import com.example.airbnb.data.ServicePool
import com.example.airbnb.data.dto.response.ResponseNickNameDto
import kotlinx.coroutines.launch

class ExampleViewModel : ViewModel() {
    private val _getNickName: MutableLiveData<UiState<ResponseNickNameDto?>> = MutableLiveData()
    val getNickName: MutableLiveData<UiState<ResponseNickNameDto?>> = _getNickName

    init {
        getNickName()
    }

    fun getNickName() = viewModelScope.launch {
        _getNickName.value = UiState.Loading
        runCatching {
            ServicePool.exampleService.getNickName(1)
        }.fold({ _getNickName.value = UiState.Success(it.data) },
            { _getNickName.value = UiState.Failure(it.message.toString()) })
    }
}