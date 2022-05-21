package com.aliosmanunaldi.wusicapp.data.login

import com.google.gson.annotations.SerializedName

data class LoginDataResponse(
    @SerializedName("userId")
    val id: Int?,
    @SerializedName("usersRoomId")
    val usersRoomId : Int?,
)
