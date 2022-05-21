package com.aliosmanunaldi.wusicapp.data.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val data: LoginDataResponse?,
    @SerializedName("susccess")
    val success: Boolean?,
    @SerializedName("messsage")
    val message: String?
)