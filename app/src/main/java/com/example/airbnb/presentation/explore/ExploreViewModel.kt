package com.example.airbnb.presentation.explore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.core.view.UiState
import com.example.airbnb.data.ServicePool
import com.example.airbnb.data.dto.response.ResponseExploreImageDto
import com.example.airbnb.data.dto.response.ResponseExploreInfoDto
import kotlinx.coroutines.launch

class ExploreViewModel : ViewModel() {

    private val _exploreImageLiveData: MutableLiveData<UiState<List<ResponseExploreImageDto>?>> =
        MutableLiveData()
    val exploreImageLiveData: MutableLiveData<UiState<List<ResponseExploreImageDto>?>> =
        _exploreImageLiveData

    private val _exploreInfoLiveData: MutableLiveData<UiState<List<ResponseExploreInfoDto>?>> =
        MutableLiveData()
    val exploreInfoLiveData: MutableLiveData<UiState<List<ResponseExploreInfoDto>?>> =
        _exploreInfoLiveData

    init {
    }

    fun getImageAndInfo(imageId: Int, dormitoryId: Int) = viewModelScope.launch {
        Log.d("getImageAndInfo", "Id: $imageId, $dormitoryId")
        _exploreImageLiveData.value = UiState.Loading
        _exploreInfoLiveData.value = UiState.Loading

        val imageResult = runCatching {
            ServicePool.exploreService.getExploreImage(imageId)
        }.onSuccess {
            // 받은 이미지 리스트
            _exploreImageLiveData.value = UiState.Success(it.data)
            Log.d("imageResult", "imageUrls:" + _exploreImageLiveData.value.toString())
        }.onFailure {
            Log.e("imageResult", "imageUrls: $it")
        }

        val infoResult = runCatching {
            ServicePool.exploreService.getExploreInfo(dormitoryId)
        }.onSuccess {
            _exploreInfoLiveData.value = UiState.Success(it.data)
            Log.d("infoResult", "infoResult:" + _exploreInfoLiveData.value.toString())
        }.onFailure {
            Log.e("infoResult", "infoResult: $it")
        }
    }
}
