package com.example.airbnb.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseNickNameDto(
    @SerialName("id")
    val id: Int,
    @SerialName("nickname")
    val nickname: String
)