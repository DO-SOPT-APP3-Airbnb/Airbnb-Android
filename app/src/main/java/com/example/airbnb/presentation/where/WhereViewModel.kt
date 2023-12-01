package com.example.airbnb.presentation.where

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.core.view.UiState
import com.example.airbnb.data.ServicePool
import kotlinx.coroutines.launch

class WhereViewModel : ViewModel() {
    private val _whereLiveData: MutableLiveData<UiState<List<WhereItem>>> = MutableLiveData()
    val whereLiveData: MutableLiveData<UiState<List<WhereItem>>> = _whereLiveData

    private val _getNickName: MutableLiveData<UiState<String?>> = MutableLiveData()
    val getNickName: MutableLiveData<UiState<String?>> = _getNickName

    init {
        getWhere()
        getNickName()
    }

    private fun getWhere() = viewModelScope.launch {
        _whereLiveData.value = UiState.Loading
        runCatching {
            ServicePool.whereService.getWhere()
        }.onSuccess {
            val whereList = it.data ?: emptyList()
            val whereItems: List<WhereItem> = whereList.map { whereList ->
                WhereItem(
                    locationName = whereList.regionName,
                    locationImageUrl = whereList.imageUrl
                )
            }

            _whereLiveData.value = UiState.Success(whereItems)
            Log.d("wv", "성공")
        }.onFailure {
            Log.d("wv", it.message.toString())
        }
    }

    private fun getNickName() = viewModelScope.launch {
        _getNickName.value = UiState.Loading
        runCatching {
            ServicePool.whereService.getNickName(1)
        }.fold({ _getNickName.value = UiState.Success(it.data?.nickname) },
            { _getNickName.value = UiState.Failure(it.message.toString()) })
    }
}