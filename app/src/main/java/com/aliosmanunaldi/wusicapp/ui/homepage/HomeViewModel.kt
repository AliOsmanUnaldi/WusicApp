package com.aliosmanunaldi.wusicapp.ui.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.data.home.HomeRepository
import com.aliosmanunaldi.wusicapp.data.home.models.RoomListResponse
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    val pageLiveData: MutableLiveData<RoomListViewState> = MutableLiveData()

    fun getPageLiveData(): LiveData<RoomListViewState> = pageLiveData

    fun fetchRoomList(userId: Int, cityName: String) = viewModelScope.launch {
        try {
            repository.fetchRoomList(userId, cityName).collect {
                pageLiveData.value = RoomListViewState(it ?: RoomListResponse(listOf()))
            }
        } catch (e: Exception) {
            pageLiveData.value = RoomListViewState(
                RoomListResponse(listOf())
            )
        }
    }
}