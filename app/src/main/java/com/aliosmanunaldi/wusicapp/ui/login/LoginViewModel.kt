package com.aliosmanunaldi.wusicapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.User
import com.aliosmanunaldi.wusicapp.data.login.LoginRepository
import com.aliosmanunaldi.wusicapp.data.login.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(
    val repository: LoginRepository
): ViewModel() {

    val pageLiveData: MutableLiveData<LoginPageViewState> = MutableLiveData()

    fun getPageLiveData(): LiveData<LoginPageViewState> = pageLiveData

    fun setUserLogin(user: User) = viewModelScope.launch {
            val result: LoginResponse =
                repository.setUserLogin(user)

        if (result.data != null){
            pageLiveData.value = LoginPageViewState(result)
        }

    }
}