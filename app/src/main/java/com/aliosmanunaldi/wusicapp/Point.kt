package com.aliosmanunaldi.wusicapp

import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("pointsOwnerId")
    val pointsOwnerId: Int,
    @SerializedName("pointsRecieverId")
    val pointsRecieverId: Int,
    @SerializedName("givenPoint")
    val givenPoint: Double
)
