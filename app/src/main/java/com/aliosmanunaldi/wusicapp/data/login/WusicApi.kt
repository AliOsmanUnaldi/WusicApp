package com.aliosmanunaldi.wusicapp.data.login

import com.aliosmanunaldi.wusicapp.UserRegister
import com.aliosmanunaldi.wusicapp.data.home.models.RoomListResponse
import com.aliosmanunaldi.wusicapp.data.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WusicApi {
    @GET("/login")
    suspend fun login(
        @Query(value = "userName") username: String,
        @Query(value = "password") password: String
    ): LoginResponse?

    @POST("/register")
    suspend fun register(@Body user: UserRegister): RegisterResponse

    @GET("/api/rooms/getAllByCityName")
    suspend fun fetchRoomList(
        @Query(value = "city") city: String,
        @Query(value = "userId") userId: String
    ): RoomListResponse
}