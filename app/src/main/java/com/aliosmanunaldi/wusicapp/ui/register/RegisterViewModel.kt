package com.aliosmanunaldi.wusicapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.UserRegister
import com.aliosmanunaldi.wusicapp.data.register.RegisterRepository
import com.aliosmanunaldi.wusicapp.data.register.RegisterResponse
import kotlinx.coroutines.launch

class RegisterViewModel(val repository: RegisterRepository) : ViewModel() {

    val pageLiveData: MutableLiveData<RegisterPageViewState> = MutableLiveData()

    fun getPageLiveData(): LiveData<RegisterPageViewState> = pageLiveData


    fun setUserRegister(user: UserRegister) = viewModelScope.launch {
        try {
            repository.setUserRegister(user).collect {
                pageLiveData.value = RegisterPageViewState(it)
            }
        } catch (e: Exception) {
            pageLiveData.value = RegisterPageViewState(
                RegisterResponse(
                    success = false,
                    message = "Kayıt başarısız kullanıcı adı daha önceden alınmış!"
                )
            )
        }
    }
}