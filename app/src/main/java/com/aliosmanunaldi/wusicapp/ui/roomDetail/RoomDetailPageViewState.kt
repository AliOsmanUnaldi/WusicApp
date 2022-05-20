package com.aliosmanunaldi.wusicapp.ui.roomDetail

import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailResponse

data class RoomDetailPageViewState(val result: RoomDetailResponse) {

    fun getRoomOwnerId() = result.data?.ownerId

    fun getRoomName() = result.data?.roomName

    fun getRoomGenre() = result.data?.genre

    fun getRoomCity() = result.data?.city

    fun getRoomDescription() = result.data?.description

    fun getAveragePoint() = result.data?.averagePoint.toString() ?: "0.0"
}
