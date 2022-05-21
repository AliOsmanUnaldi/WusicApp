package com.aliosmanunaldi.wusicapp.data.roomDetail.comment

import com.google.gson.annotations.SerializedName

data class CommentListResponse(
    @SerializedName("data")
    val data: List<CommentResponse?>?,
    @SerializedName("messsage")
    val message: String,
    @SerializedName("susccess")
    val success: Boolean
)