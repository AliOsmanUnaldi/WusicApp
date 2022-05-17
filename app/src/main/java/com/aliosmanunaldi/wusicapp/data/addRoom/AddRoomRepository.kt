package com.aliosmanunaldi.wusicapp.data.addRoom

import com.aliosmanunaldi.wusicapp.data.login.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddRoomRepository {

    suspend fun setRoom(addRoomRequest: AddRoomRequest): Flow<AddRoomResponse> {
        return flow { emit(api.setRoom(addRoomRequest)) }
    }
}