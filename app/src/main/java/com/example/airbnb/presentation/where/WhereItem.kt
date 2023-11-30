package com.example.airbnb.presentation.where

data class WhereItem(
    val locationName: String,
    val locationImageUrl: String,
    var isClicked: Boolean = false
)