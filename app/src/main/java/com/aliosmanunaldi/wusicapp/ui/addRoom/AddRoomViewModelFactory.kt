package com.aliosmanunaldi.wusicapp.ui.addRoom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliosmanunaldi.wusicapp.data.addRoom.AddRoomRepository

@Suppress("UNCHECKED_CAST")
class AddRoomViewModelFactory(
    private val repository: AddRoomRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddRoomViewModel(repository) as T
    }

}