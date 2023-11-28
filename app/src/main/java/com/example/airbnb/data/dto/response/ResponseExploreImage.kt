package com.example.airbnb.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseExploreImage(
    @SerialName("imageUrl")
    val imageUrl: String,
)
