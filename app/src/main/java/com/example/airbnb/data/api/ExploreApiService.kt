package com.example.airbnb.data.api

import com.example.airbnb.data.dto.BaseResponse
import com.example.airbnb.data.dto.response.ResponseExploreImage
import retrofit2.http.GET
import retrofit2.http.Path

interface ExploreApiService {
    companion object {
        const val API = "api"
        const val DORMITORY = "dormitory"
        const val IMAGE = "image"
    }

    @GET("$API/$DORMITORY/$IMAGE/{:imageId}")
    suspend fun getExploreImage(
        @Path("imageId") imageId: Int,
    ): BaseResponse<ResponseExploreImage>
}
