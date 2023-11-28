package com.example.airbnb.data.api

import com.example.airbnb.data.dto.BaseResponse
import com.example.airbnb.data.dto.response.ResponseExploreImageDto
import com.example.airbnb.data.dto.response.ResponseExploreInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ExploreApiService {
    companion object {
        const val API = "api"
        const val DORMITORY = "dormitory"
    }

    @GET("$API/$DORMITORY/image/{imageId}")
    suspend fun getExploreImage(
        @Path("imageId") imageId: Int,
    ): BaseResponse<ResponseExploreImageDto>

    @GET("$API/$DORMITORY/{dormitoryId}")
    suspend fun getExploreInfo(
        @Path("dormitoryId") dormitoryId: Int,
    ): BaseResponse<ResponseExploreInfoDto>
}
