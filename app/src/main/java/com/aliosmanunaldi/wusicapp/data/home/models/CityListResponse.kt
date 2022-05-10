package com.aliosmanunaldi.wusicapp.data.home.models

import com.google.gson.annotations.SerializedName

data class CityListResponse(
    @SerializedName("data")
    val cityList: List<CityResponse>
)
