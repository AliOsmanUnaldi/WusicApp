package com.aliosmanunaldi.wusicapp.data.roomDetail

import com.aliosmanunaldi.wusicapp.data.home.models.RoomResponse
import com.google.gson.annotations.SerializedName

data class RoomDetailResponse(

    @SerializedName("data")
    val data: RoomResponse?,

    @SerializedName("susccess")
    val success: Boolean,

    @SerializedName("messsage")
    val message: String
)
