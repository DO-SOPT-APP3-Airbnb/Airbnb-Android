package com.example.airbnb.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseWhereDto(
    @SerialName("regionName")
    val regionName: String,
    @SerialName("imageUrl")
    val imageUrl: String,
)