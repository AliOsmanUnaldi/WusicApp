package com.aliosmanunaldi.wusicapp.ui.homepage.roomDetail

import androidx.lifecycle.ViewModel
import com.aliosmanunaldi.wusicapp.data.roomDetail.RoomDetailRepository

class RoomDetailViewModel(val repository: RoomDetailRepository) : ViewModel() {

/*    val pageLiveData: MutableLiveData<RoomDetailPageViewState> = MutableLiveData()

    fun getPageLiveData(): LiveData<RoomDetailPageViewState> = pageLiveData


    fun setRoomDetail(room: RoomDetail) = viewModelScope.launch {
        try {
            repository.setRoomDetail(room).collect {
                pageLiveData.value = RoomDetailPageViewState(it)
            }
        } catch (e: Exception) {
            pageLiveData.value = RoomDetailPageViewState(
                RegisterResponse(
                    success = false,
                    message = "Kayıt başarısız kullanıcı adı daha önceden alınmış!"
                )
            )
        }
    }
    */
}