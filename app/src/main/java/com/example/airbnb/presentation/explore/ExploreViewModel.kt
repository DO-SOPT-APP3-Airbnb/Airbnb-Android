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

    fun getImageAndInfo(imageId: Int, dormitoryId: Int) = viewModelScope.launch {
        _exploreImageLiveData.value = UiState.Loading
        _exploreInfoLiveData.value = UiState.Loading

        val imageResult = runCatching {
            ServicePool.exploreService.getExploreImage(imageId)
        }

        val infoResult = runCatching {
            ServicePool.exploreService.getExploreInfo(dormitoryId)
        }

        imageResult.fold(
            { _exploreImageLiveData.value = UiState.Success(it) },
            { _exploreImageLiveData.value = UiState.Failure(it.message.toString()) }
        )

        infoResult.fold(
            { _exploreInfoLiveData.value = UiState.Success(it) },
            { _exploreInfoLiveData.value = UiState.Failure(it.message.toString()) }
        )
    }
}
