package com.aliosmanunaldi.wusicapp.ui.roomDetail

import android.view.View
import com.aliosmanunaldi.wusicapp.data.roomDetail.JoinRoomResponse

data class JoinRoomViewState(val response: JoinRoomResponse) {

    fun isUserJoined(): Int {
        return if (response.success) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    fun isUserNotJoined(): Int {
        return if (response.success) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}