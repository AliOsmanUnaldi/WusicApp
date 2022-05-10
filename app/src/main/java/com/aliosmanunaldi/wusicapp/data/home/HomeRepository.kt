package com.aliosmanunaldi.wusicapp.data.home

import com.aliosmanunaldi.wusicapp.data.home.models.RoomListResponse
import com.aliosmanunaldi.wusicapp.data.login.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository {

    suspend fun fetchRoomList(userId: Int, cityName: String): Flow<RoomListResponse?> {

        return flow { emit(api.fetchRoomList(cityName, userId.toString())) }

    }
}