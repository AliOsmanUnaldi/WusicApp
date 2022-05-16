package com.aliosmanunaldi.wusicapp

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("createPointRequest")
    val point: Point,
    @SerializedName("createCommentRequest")
    val comment: Comment
)
