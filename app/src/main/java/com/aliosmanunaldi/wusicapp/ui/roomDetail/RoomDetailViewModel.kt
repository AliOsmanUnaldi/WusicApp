package com.aliosmanunaldi.wusicapp.ui.roomDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.data.roomDetail.JoinRoomResponse
import com.aliosmanunaldi.wusicapp.data.roomDetail.LeaveRoomResponse
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailRepository
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailResponse
import kotlinx.coroutines.launch

class RoomDetailViewModel(val repository: RoomDetailRepository) : ViewModel() {


    val pageLiveData: MutableLiveData<RoomDetailPageViewState> = MutableLiveData()
    val joinRoomLiveData: MutableLiveData<JoinRoomViewState> = MutableLiveData()
    val leaveRoomLiveData: MutableLiveData<LeaveRoomViewState> = MutableLiveData()

    fun getPageLiveData(): LiveData<RoomDetailPageViewState> = pageLiveData
    fun getJoinRoomLiveData(): LiveData<JoinRoomViewState> = joinRoomLiveData
    fun getQuitRoomLiveData(): LiveData<LeaveRoomViewState> = leaveRoomLiveData


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

    fun setUserJoinRoom(userId: Int, roomId: Int) = viewModelScope.launch {
        try {
            repository.setUserJoinRoom(userId, roomId).collect {
                joinRoomLiveData.value = JoinRoomViewState(it)
            }
        } catch (e: Exception) {
            joinRoomLiveData.value = JoinRoomViewState(
                JoinRoomResponse(
                    success = false,
                    message = "OLMADI"
                )
            )
        }
    }

    fun setUserQuitRoom(userId: Int) = viewModelScope.launch {
        try {
            repository.setUserQuitRoom(userId).collect {
                leaveRoomLiveData.value = LeaveRoomViewState(it)
            }
        } catch (e: Exception) {
            leaveRoomLiveData.value = LeaveRoomViewState(
                LeaveRoomResponse(
                    success = false,
                    message = "OLMADI"
                )
            )
        }
    }

}