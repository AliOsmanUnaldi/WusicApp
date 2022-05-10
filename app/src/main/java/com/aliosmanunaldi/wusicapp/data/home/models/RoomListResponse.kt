package com.aliosmanunaldi.wusicapp.data.home.models

import com.google.gson.annotations.SerializedName

data class RoomListResponse(
    @SerializedName("data")
    val rooms: List<RoomResponse>
)
