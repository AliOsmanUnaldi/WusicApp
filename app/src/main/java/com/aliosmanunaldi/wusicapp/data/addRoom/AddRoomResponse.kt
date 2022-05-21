package com.aliosmanunaldi.wusicapp.data.addRoom

import com.google.gson.annotations.SerializedName

data class AddRoomResponse(
    @SerializedName("data")
    val data: Int?,
    @SerializedName("susccess")
    val success: Boolean,
    @SerializedName("messsage")
    val message: String
)
