package com.aliosmanunaldi.wusicapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliosmanunaldi.wusicapp.data.login.LoginRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val repository: LoginRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}