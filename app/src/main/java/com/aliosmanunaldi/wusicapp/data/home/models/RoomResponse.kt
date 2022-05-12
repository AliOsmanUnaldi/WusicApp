package com.aliosmanunaldi.wusicapp.data.home.models

import com.google.gson.annotations.SerializedName

data class RoomResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("ownerId")
    val ownerId: Int?,
    @SerializedName("roomName")
    val roomName: String?,
    @SerializedName("genre")
    val genre: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("description")
    val description: String?
)