package com.aliosmanunaldi.wusicapp.ui.myroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.data.myroom.MyRoomRepository
import kotlinx.coroutines.launch

class MyRoomViewModel(
    val repository: MyRoomRepository
) : ViewModel() {

    private val participantListLiveData: MutableLiveData<List<String>> = MutableLiveData()

    fun getParticipantListLiveData(): LiveData<List<String>> = participantListLiveData

    fun getParticipantList(id: Int) = viewModelScope.launch {
        try {
            repository.getParticipantList(id).collect {
                participantListLiveData.value = it
            }
        } catch (e: Exception) {
        }
    }
}