package com.aliosmanunaldi.wusicapp.ui.roomDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.data.roomDetail.JoinRoomResponse
import com.aliosmanunaldi.wusicapp.data.roomDetail.LeaveRoomResponse
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailRepository
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailResponse
import com.aliosmanunaldi.wusicapp.data.roomDetail.comment.CommentListResponse
import com.aliosmanunaldi.wusicapp.ui.roomDetail.comment.CommentsViewState
import kotlinx.coroutines.launch

class RoomDetailViewModel(val repository: RoomDetailRepository) : ViewModel() {


    private val pageLiveData: MutableLiveData<RoomDetailPageViewState> = MutableLiveData()
    private val joinRoomLiveData: MutableLiveData<JoinRoomViewState> = MutableLiveData()
    private val leaveRoomLiveData: MutableLiveData<LeaveRoomViewState> = MutableLiveData()
    private val commentsLiveData: MutableLiveData<CommentsViewState> = MutableLiveData()

    fun getPageLiveData(): LiveData<RoomDetailPageViewState> = pageLiveData
    fun getJoinRoomLiveData(): LiveData<JoinRoomViewState> = joinRoomLiveData
    fun getQuitRoomLiveData(): LiveData<LeaveRoomViewState> = leaveRoomLiveData
    fun getCommentsLiveData(): LiveData<CommentsViewState> = commentsLiveData


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

    fun fetchComments(ownerId: Int) = viewModelScope.launch {
        try {
            repository.fetchComments(ownerId).collect{
                commentsLiveData.value = CommentsViewState(it)
            }
        } catch (e: Exception){
            commentsLiveData.value = CommentsViewState(
                CommentListResponse(
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