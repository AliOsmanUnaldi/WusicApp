package com.aliosmanunaldi.wusicapp.ui.homepage.roomDetail

import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailResponse

data class RoomDetailPageViewState(val result: RoomDetailResponse) {


    fun getRoomName() = result.data?.roomName

    fun getRoomGenre() = result.data?.genre

    fun getRoomCity() = result.data?.city

    fun getRoomDescription() = result.data?.description
}
