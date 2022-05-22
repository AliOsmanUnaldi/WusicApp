package com.aliosmanunaldi.wusicapp.data.login

import com.aliosmanunaldi.wusicapp.Review
import com.aliosmanunaldi.wusicapp.UserRegister
import com.aliosmanunaldi.wusicapp.data.addRoom.AddRoomRequest
import com.aliosmanunaldi.wusicapp.data.addRoom.AddRoomResponse
import com.aliosmanunaldi.wusicapp.data.home.models.CityListResponse
import com.aliosmanunaldi.wusicapp.data.home.models.RoomListResponse
import com.aliosmanunaldi.wusicapp.data.myroom.MyRoomResponse
import com.aliosmanunaldi.wusicapp.data.register.RegisterResponse
import com.aliosmanunaldi.wusicapp.data.review.ReviewResponse
import com.aliosmanunaldi.wusicapp.data.roomDetail.JoinRoomResponse
import com.aliosmanunaldi.wusicapp.data.roomDetail.LeaveRoomResponse
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailResponse
import com.aliosmanunaldi.wusicapp.data.roomDetail.comment.CommentListResponse
import retrofit2.http.*

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

    @GET("/api/cities/getAll")
    suspend fun fetchCities(): CityListResponse

    @GET("/api/rooms/getRoomByRoomId")
    suspend fun getRoomByRoomId(@Query(value = "id") roomId: Int): RoomDetailResponse

    @GET("/api/comments/getCommentsByCommentRecieversId")
    suspend fun fetchCommentsByRoomOwnerId(@Query(value = "id") ownerId: Int): CommentListResponse

    @GET("/joinIntoRoom")
    suspend fun setUserJoinRoom(
        @Query(value = "userId") userId: Int,
        @Query(value = "roomId") roomId: Int
    ): JoinRoomResponse

    @GET("/quit")
    suspend fun setUserQuitRoom(
        @Query(value = "id") userId: Int
    ): LeaveRoomResponse

    @POST("/leaveFromRoom")
    suspend fun setUserReview(
        @Body review: Review
    ): ReviewResponse

    @POST(value = ("/api/rooms/add"))
    suspend fun setRoom(
        @Body addRoomRequest: AddRoomRequest
    ): AddRoomResponse

    @GET("/api/rooms/getAllParticipants")
    suspend fun getParticipantList(
        @Query(value = "roomId") roomId: Int
    ): List<String>

    @DELETE("/api/rooms/delete")
    suspend fun removeMyRoom(
        @Query(value = "id") id: Int
    ): MyRoomResponse
}