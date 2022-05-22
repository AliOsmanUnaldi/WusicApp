package com.aliosmanunaldi.wusicapp.data.myroom

import com.aliosmanunaldi.wusicapp.data.login.LoginDataResponse
import com.google.gson.annotations.SerializedName

data class MyRoomResponse(
    @SerializedName("susccess")
    val success: Boolean?,
    @SerializedName("messsage")
    val message: String?
)