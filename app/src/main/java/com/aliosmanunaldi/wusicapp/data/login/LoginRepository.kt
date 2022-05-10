package com.aliosmanunaldi.wusicapp.data.login

import com.aliosmanunaldi.wusicapp.User

class LoginRepository{

    suspend fun setUserLogin(user: User) :LoginResponse{
        return api.login(user.username,user.password)
    }
}