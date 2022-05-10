package com.aliosmanunaldi.wusicapp.data.register

import com.aliosmanunaldi.wusicapp.UserRegister
import com.aliosmanunaldi.wusicapp.data.login.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterRepository {

    suspend fun setUserRegister(user: UserRegister): Flow<RegisterResponse> {
        return flow { emit(api.register(user)) }
    }
}