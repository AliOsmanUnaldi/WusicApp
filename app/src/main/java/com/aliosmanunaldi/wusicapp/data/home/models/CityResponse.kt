package com.aliosmanunaldi.wusicapp.data.home.models

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("city")
    val city: String
)
