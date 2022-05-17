package com.aliosmanunaldi.wusicapp.data.addRoom

import com.google.gson.annotations.SerializedName

data class AddRoomRequest(

    @SerializedName("ownerId")
    val ownerId: Int,
    @SerializedName("roomName")
    val roomName: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("city")
    val cityId: Int,
    @SerializedName("password")
    val password: String,
    @SerializedName("description")
    val description: String
)
