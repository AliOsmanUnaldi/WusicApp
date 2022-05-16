package com.aliosmanunaldi.wusicapp

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("commentsOwnerId")
    val commentsOwnerId: Int,
    @SerializedName("commentsRecieverId")
    val commentsRecieverId: Int,
    @SerializedName("commentText")
    val commentText: String
)
