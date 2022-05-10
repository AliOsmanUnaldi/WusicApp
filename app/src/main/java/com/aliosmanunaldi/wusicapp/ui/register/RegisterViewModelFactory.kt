package com.aliosmanunaldi.wusicapp.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliosmanunaldi.wusicapp.data.register.RegisterRepository

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory(
    private val repository: RegisterRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(repository) as T
    }
}