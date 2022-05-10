package com.aliosmanunaldi.wusicapp.data.login

import com.aliosmanunaldi.wusicapp.User
import retrofit2.Response
import retrofit2.http.*

interface WusicApi {
    @GET("/login")
    suspend fun login(@Query(value = "userName") username: String,@Query(value = "password") password: String): LoginResponse
}