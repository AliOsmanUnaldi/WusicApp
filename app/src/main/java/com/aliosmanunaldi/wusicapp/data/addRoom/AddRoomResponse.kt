package com.aliosmanunaldi.wusicapp.data.addRoom

import com.google.gson.annotations.SerializedName

data class AddRoomResponse(
    @SerializedName("susccess")
    val success: Boolean,
    @SerializedName("messsage")
    val message: String
)
