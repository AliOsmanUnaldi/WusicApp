package com.aliosmanunaldi.wusicapp.ui.homepage.roomDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailRepository

@Suppress("UNCHECKED_CAST")
class RoomDetailViewModelFactory(
    private val repository: RoomDetailRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RoomDetailViewModel(repository) as T
    }
}