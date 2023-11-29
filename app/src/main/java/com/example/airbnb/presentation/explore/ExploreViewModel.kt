package com.example.airbnb.presentation.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.core.view.UiState
import com.example.airbnb.data.ServicePool
import com.example.airbnb.data.dto.BaseResponse
import com.example.airbnb.data.dto.response.ResponseExploreImageDto
import com.example.airbnb.data.dto.response.ResponseExploreInfoDto
import kotlinx.coroutines.launch

class ExploreViewModel : ViewModel() {

    private val _exploreImageLiveData: MutableLiveData<UiState<BaseResponse<ResponseExploreImageDto>>> =
        MutableLiveData()
    val exploreImageLiveData: MutableLiveData<UiState<BaseResponse<ResponseExploreImageDto>>> =
        _exploreImageLiveData

    private val _exploreInfoLiveData: MutableLiveData<UiState<BaseResponse<ResponseExploreInfoDto>>> =
        MutableLiveData()
    val exploreInfoLiveData: MutableLiveData<UiState<BaseResponse<ResponseExploreInfoDto>>> =
        _exploreInfoLiveData

    init {
    }

    fun getImage(imageId: Int) = viewModelScope.launch {
        _exploreImageLiveData.value = UiState.Loading
        runCatching {
            ServicePool.exploreService.getExploreImage(imageId)
        }.fold(
            {
                _exploreImageLiveData.value = UiState.Success(it)
            },
            {
                _exploreImageLiveData.value = UiState.Failure(it.message.toString())
            },
        )
    }

    fun getInfo(dormitoryId: Int) = viewModelScope.launch {
        _exploreInfoLiveData.value = UiState.Loading
        runCatching {
            ServicePool.exploreService.getExploreInfo(dormitoryId)
        }.fold(
            {
                _exploreInfoLiveData.value = UiState.Success(it)
            },
            { _exploreInfoLiveData.value = UiState.Failure(it.message.toString()) },
        )
    }
}
