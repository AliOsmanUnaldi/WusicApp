package com.aliosmanunaldi.wusicapp.ui.myroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.data.myroom.MyRoomRepository
import com.aliosmanunaldi.wusicapp.data.myroom.MyRoomResponse
import kotlinx.coroutines.launch

class MyRoomViewModel(
    val repository: MyRoomRepository
) : ViewModel() {

    private val participantListLiveData: MutableLiveData<List<String>> = MutableLiveData()
    private val removeRoomLiveData: MutableLiveData<MyRoomResponse> = MutableLiveData()

    fun getParticipantListLiveData(): LiveData<List<String>> = participantListLiveData
    fun getRemoveRoomLiveData(): LiveData<MyRoomResponse> = removeRoomLiveData

    fun getParticipantList(id: Int) = viewModelScope.launch {
        try {
            repository.getParticipantList(id).collect {
                participantListLiveData.value = it
            }
        } catch (e: Exception) {
        }
    }

    fun removeMyRoom(id: Int) = viewModelScope.launch {
        try {
            repository.removeMyRoom(id).collect {
                removeRoomLiveData.value = it
            }
        } catch (e: Exception) {
            removeRoomLiveData.value = MyRoomResponse(
                success = false,
                message = "Oda silinemedi!"
            )
        }
    }
}