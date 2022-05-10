package com.aliosmanunaldi.wusicapp.ui.homepage

import com.aliosmanunaldi.wusicapp.data.home.models.RoomResponse

data class RoomItemViewState(val room: RoomResponse) {

    fun getRoomName() = room.roomName

    fun getRoomGenre() = room.genre
}