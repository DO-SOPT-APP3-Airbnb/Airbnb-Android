package com.example.airbnb.data.api

import com.example.airbnb.data.dto.BaseResponse
import com.example.airbnb.data.dto.response.ResponseWhereDto
import retrofit2.http.GET

interface WhereApiService {
    companion object {
        const val API = "api"
        const val REGION = "region"
    }

    @GET("$API/$REGION")
    suspend fun getWhere(): BaseResponse<List<ResponseWhereDto>>
}