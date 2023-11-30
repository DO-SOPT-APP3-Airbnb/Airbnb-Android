package com.example.airbnb.presentation.explore

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

    private val _exploreInfoLiveData: MutableLiveData<UiState<List<ExploreInfoData>>> =
        MutableLiveData()
    val exploreImageLiveData: MutableLiveData<UiState<List<ExploreInfoData>>> =
        _exploreInfoLiveData

    fun getImageAndInfo(imageId: Int, dormitoryId: Int) = viewModelScope.launch {
        _exploreInfoLiveData.value = UiState.Loading
        lateinit var imgList: List<ResponseExploreImageDto>
        lateinit var infoList: List<ResponseExploreInfoDto>

        // 이미지 url api
        runCatching {
            ServicePool.exploreService.getExploreImage(imageId)
        }.onSuccess {
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
            infoList = it.data ?: emptyList()
            Timber.d("성공")
        }.onFailure {
            Timber.d("실패")
        }

        // info, url 리스트 합치기
        val mergedList: List<ExploreInfoData> = imgList.zip(infoList) { image, info ->
            ExploreInfoData(
                Image = image.imageUrl,
                description = info.description,
                distance = info.distance,
                travelDate = info.travelDate,
                price = info.price,
                score = info.scope,
            )
        }
        _exploreInfoLiveData.value = UiState.Success(mergedList)
    }
}
