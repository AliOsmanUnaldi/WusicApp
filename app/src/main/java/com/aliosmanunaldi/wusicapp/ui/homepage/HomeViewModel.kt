package com.aliosmanunaldi.wusicapp.ui.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliosmanunaldi.wusicapp.data.home.HomeRepository
import com.aliosmanunaldi.wusicapp.data.home.models.CityListResponse
import com.aliosmanunaldi.wusicapp.data.home.models.RoomListResponse
import com.aliosmanunaldi.wusicapp.ui.city.CityListViewState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    val pageLiveData: MutableLiveData<RoomListViewState> = MutableLiveData()
    val citiesLiveData: MutableLiveData<CityListViewState> = MutableLiveData()

    fun getPageLiveData(): LiveData<RoomListViewState> = pageLiveData
    fun getCitesLiveData(): LiveData<CityListViewState> = citiesLiveData

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

    fun fetchCities() = viewModelScope.launch {
        try {
            repository.fetchCities().collect {
                citiesLiveData.value = CityListViewState(it ?: CityListResponse(listOf()))
            }
        } catch (e: Exception) {
            citiesLiveData.value = CityListViewState(
                CityListResponse(listOf())
            )
        }
    }
}