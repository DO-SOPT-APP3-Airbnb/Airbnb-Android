package com.example.airbnb.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseExploreInfoDto(
    @SerialName("description")
    val description: String,
    @SerialName("distance")
    val distance: Int,
    @SerialName("travelDate")
    val travelDate: String,
    @SerialName("price")
    val price: Int,
    @SerialName("scope")
    val scope: Double,
)
