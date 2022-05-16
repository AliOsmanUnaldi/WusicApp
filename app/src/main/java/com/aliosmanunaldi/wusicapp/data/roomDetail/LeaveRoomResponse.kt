package com.aliosmanunaldi.wusicapp.data.roomDetail

import com.google.gson.annotations.SerializedName

data class LeaveRoomResponse(
    @SerializedName("susccess")
    val success: Boolean,

    @SerializedName("messsage")
    val message: String
)
