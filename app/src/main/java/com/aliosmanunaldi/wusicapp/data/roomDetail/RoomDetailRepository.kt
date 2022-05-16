package com.aliosmanunaldi.wusicapp.data.roomDetail

import com.aliosmanunaldi.wusicapp.data.login.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoomDetailRepository {

    suspend fun fetchRoomDetail(roomId: Int): Flow<RoomDetailResponse> {
        return flow { emit(api.getRoomByRoomId(roomId)) }
    }

    suspend fun setUserJoinRoom(userId: Int, roomId: Int): Flow<JoinRoomResponse> {
        return flow { emit(api.setUserJoinRoom(userId, roomId)) }
    }

    suspend fun setUserQuitRoom(userId: Int): Flow<LeaveRoomResponse> {
        return flow { emit(api.setUserQuitRoom(userId)) }
    }
}