package com.aliosmanunaldi.wusicapp.data.myroom

import com.aliosmanunaldi.wusicapp.data.login.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyRoomRepository {

    suspend fun getParticipantList(id: Int): Flow<List<String>> {
        return flow { emit(api.getParticipantList(id)) }
    }

    suspend fun removeMyRoom(id: Int): Flow<MyRoomResponse>{
        return flow { emit(api.removeMyRoom(id)) }
    }
}