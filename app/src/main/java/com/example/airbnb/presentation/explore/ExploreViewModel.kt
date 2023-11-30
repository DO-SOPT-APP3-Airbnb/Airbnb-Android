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

    private val _exploreViewPagerLiveData: MutableLiveData<UiState<List<ExploreInfoData>>> =
        MutableLiveData()
    val exploreImageLiveData: MutableLiveData<UiState<List<ExploreInfoData>>> =
        _exploreViewPagerLiveData

    fun getImageAndInfo(imageId: Int, dormitoryId: Int) = viewModelScope.launch {
        _exploreViewPagerLiveData.value = UiState.Loading
        lateinit var imgApiList: List<ResponseExploreImageDto>
        lateinit var infoApiList: List<ResponseExploreInfoDto>

        // 이미지 url api
        runCatching {
            ServicePool.exploreService.getExploreImage(imageId)
        }.onSuccess {
            imgApiList = it.data ?: emptyList()
            Timber.d("성공")
        }.onFailure {
            Timber.d("실패")
        }

        // 이미지 정보 api
        runCatching {
            ServicePool.exploreService.getExploreInfo(dormitoryId)
        }.onSuccess {
            // 받은 정보 리스트
            infoApiList = it.data ?: emptyList()
            Timber.d("성공")
        }.onFailure {
            Timber.d("실패")
        }

        // info, url 리스트 합치기
        val mergedList: List<ExploreInfoData> = imgApiList.zip(infoApiList) { image, info ->
            ExploreInfoData(
                Image = image.imageUrl,
                description = info.description,
                distance = info.distance,
                travelDate = info.travelDate,
                price = info.price,
                scope = info.scope,
            )
        }
        _exploreViewPagerLiveData.value = UiState.Success(mergedList)
    }
}
