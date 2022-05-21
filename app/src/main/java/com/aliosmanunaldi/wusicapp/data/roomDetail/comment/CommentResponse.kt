package com.aliosmanunaldi.wusicapp.data.roomDetail.comment

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("commentOwnerName")
    val name: String,
    @SerializedName("commentText")
    val comment: String,
)