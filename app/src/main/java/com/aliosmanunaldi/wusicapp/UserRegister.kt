package com.aliosmanunaldi.wusicapp

import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName(value = "userName")
    val username: String,
    @SerializedName(value = "password")
    val password: String,
    @SerializedName(value = "email")
    val email: String
)
