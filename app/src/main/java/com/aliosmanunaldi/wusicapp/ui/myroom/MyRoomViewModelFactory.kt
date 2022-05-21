package com.aliosmanunaldi.wusicapp.ui.myroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliosmanunaldi.wusicapp.data.myroom.MyRoomRepository

@Suppress("UNCHECKED_CAST")
class MyRoomViewModelFactory(
    private val repository: MyRoomRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyRoomViewModel(repository) as T
    }
}