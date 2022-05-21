package com.aliosmanunaldi.wusicapp.ui.addRoom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.data.addRoom.AddRoomRepository
import com.aliosmanunaldi.wusicapp.data.addRoom.AddRoomRequest
import com.aliosmanunaldi.wusicapp.data.addRoom.AddRoomResponse
import kotlinx.coroutines.launch

class AddRoomViewModel(
    val repository: AddRoomRepository
) : ViewModel() {

    private val pageLiveData: MutableLiveData<AddRoomPageViewState> = MutableLiveData()

    fun getPageLiveData(): LiveData<AddRoomPageViewState> = pageLiveData

    fun setRoom(addRoomRequest: AddRoomRequest) = viewModelScope.launch {
        try {
            repository.setRoom(addRoomRequest).collect {
                pageLiveData.value = AddRoomPageViewState(it)
            }
        } catch (e: Exception) {
            pageLiveData.value = AddRoomPageViewState(
                AddRoomResponse(
                    data = null,
                    success = false,
                    message = "Kayıt başarısız!"
                )
            )
        }
    }
}