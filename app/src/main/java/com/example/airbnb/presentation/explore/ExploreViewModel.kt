package com.example.airbnb.presentation.explore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airbnb.core.view.UiState
import com.example.airbnb.data.ExploreInfoData
import com.example.airbnb.data.ServicePool
import com.example.airbnb.data.dto.response.ResponseExploreImageDto
import com.example.airbnb.data.dto.response.ResponseExploreInfoDto
import kotlinx.coroutines.launch
import timber.log.Timber

class ExploreViewModel : ViewModel() {

//    private val _exploreImageLiveData: MutableLiveData<UiState<List<ResponseExploreImageDto>?>> =
//        MutableLiveData()
//    val exploreImageLiveData: MutableLiveData<UiState<List<ResponseExploreImageDto>?>> =
//        _exploreImageLiveData
//
//    private val _exploreInfoLiveData: MutableLiveData<UiState<List<ResponseExploreInfoDto>?>> =
//        MutableLiveData()
//    val exploreInfoLiveData: MutableLiveData<UiState<List<ResponseExploreInfoDto>?>> =
//        _exploreInfoLiveData

    private val _exploreInfoLiveData: MutableLiveData<UiState<List<ExploreInfoData>>> =
        MutableLiveData()
    val exploreImageLiveData: MutableLiveData<UiState<List<ExploreInfoData>>> =
        _exploreInfoLiveData


    init {
    }

    fun getImageAndInfo(imageId: Int, dormitoryId: Int) = viewModelScope.launch {
        //_exploreImageLiveData.value = UiState.Loading
        _exploreInfoLiveData.value = UiState.Loading
        lateinit var imgList: List<ResponseExploreImageDto>
        lateinit var infoList: List<ResponseExploreInfoDto>
        // 이미지 url api
        runCatching {
            ServicePool.exploreService.getExploreImage(imageId)
        }.onSuccess {
            // 받은 이미지 리스트
            //_exploreImageLiveData.value = UiState.Success(it.data)
            imgList = it.data ?: emptyList()
            Timber.d("성공")
        }.onFailure {
            Timber.d("실패")
        }

        // 이미지 정보 api
        runCatching {
            ServicePool.exploreService.getExploreInfo(dormitoryId)
        }.onSuccess {
            // 받은 정보 리스트
            //_exploreInfoLiveData.value = UiState.Success(it.data)
            infoList = it.data ?: emptyList()
            Timber.d("성공")
        }.onFailure {
            Timber.d("실패")
        }

        val mergedList: List<ExploreInfoData> = imgList.zip(infoList) { image, info ->
            ExploreInfoData(
                Image = image.imageUrl,
                description = info.description,
                distance = info.distance,
                travelDate = info.travelDate,
                price = info.price,
                score = info.scope
            )
        }

        _exploreInfoLiveData.value = UiState.Success(mergedList)
        Log.d("ttt", _exploreInfoLiveData.value.toString())
    }
}
