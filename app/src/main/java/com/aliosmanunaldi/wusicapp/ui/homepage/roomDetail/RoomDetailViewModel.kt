package com.aliosmanunaldi.wusicapp.ui.homepage.roomDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailRepository
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailResponse
import kotlinx.coroutines.launch

class RoomDetailViewModel(val repository: RoomDetailRepository) : ViewModel() {


    val pageLiveData: MutableLiveData<RoomDetailPageViewState> = MutableLiveData()

    fun getPageLiveData(): LiveData<RoomDetailPageViewState> = pageLiveData


    fun fetchRoomDetail(roomId: Int) = viewModelScope.launch {
        try {
            repository.fetchRoomDetail(roomId).collect {
                pageLiveData.value = RoomDetailPageViewState(it)
            }
        } catch (e: Exception) {
            pageLiveData.value = RoomDetailPageViewState(
                RoomDetailResponse(
                    data = null,
                    success = false,
                    message = "Kayıt başarısız!"
                )
            )
        }
    }

    fun setUserLogin(userId: Int, roomId: Int) {

    }

}