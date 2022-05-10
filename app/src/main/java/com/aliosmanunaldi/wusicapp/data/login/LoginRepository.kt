package com.aliosmanunaldi.wusicapp.data.login

import com.aliosmanunaldi.wusicapp.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepository {

    suspend fun setUserLogin(user: User): Flow<LoginResponse?> {

        return flow { emit(api.login(user.username, user.password)) }

    }
}