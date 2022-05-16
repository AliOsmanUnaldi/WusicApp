package com.aliosmanunaldi.wusicapp.data.review

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("susccess")
    val success: Boolean?,
    @SerializedName("messsage")
    val message: String?
)
