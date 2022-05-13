package com.aliosmanunaldi.wusicapp.data.roomDetail

import com.aliosmanunaldi.wusicapp.data.login.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoomDetailRepository {
    suspend fun fetchRoomDetail(roomId: Int): Flow<RoomDetailResponse> {
        return flow { emit(api.getRoomByRoomId(roomId)) }
    }
}