package com.example.airbnb.data.api

import com.example.airbnb.data.dto.BaseResponse
import com.example.airbnb.data.dto.response.ResponseNickNameDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ExampleApiService {
    companion object {
        const val API = "api"
        const val USER = "user"
        const val USER_ID = "userId"
    }

    @GET("/$API/$USER/{$USER_ID}")
    suspend fun getNickName(
        @Path(USER_ID) userId: Int
    ): BaseResponse<ResponseNickNameDto>
}