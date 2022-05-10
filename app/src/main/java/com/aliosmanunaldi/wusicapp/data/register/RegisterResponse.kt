package com.aliosmanunaldi.wusicapp.data.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("susccess")
    val success: Boolean?,
    @SerializedName("messsage")
    val message: String?
)
