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

    private val _exploreImageList: MutableLiveData<UiState<BaseResponse<ResponseExploreImageDto>>> =
        MutableLiveData()
    val exploreImageList: MutableLiveData<UiState<BaseResponse<ResponseExploreImageDto>>> =
        _exploreImageList

    private val _exploreInfoList: MutableLiveData<UiState<BaseResponse<ResponseExploreInfoDto>>> =
        MutableLiveData()
    val exploreInfoList: MutableLiveData<UiState<BaseResponse<ResponseExploreInfoDto>>> =
        _exploreInfoList

    init {
    }

    fun getImage(imageId: Int) = viewModelScope.launch {
        _exploreImageList.value = UiState.Loading
        runCatching {
            ServicePool.exploreService.getExploreImage(imageId)
        }.fold(
            {
                _exploreImageList.value = UiState.Success(it)
            },
            { _exploreImageList.value = UiState.Failure(it.message.toString()) },
        )
    }

    fun getInfo(dormitoryId: Int) = viewModelScope.launch {
        _exploreInfoList.value = UiState.Loading
        runCatching {
            ServicePool.exploreService.getExploreInfo(dormitoryId)
        }.fold(
            {
                _exploreInfoList.value = UiState.Success(it)
            },
            { _exploreInfoList.value = UiState.Failure(it.message.toString()) },
        )
    }
}
