package com.aliosmanunaldi.wusicapp.ui.addRoom

import android.view.View
import com.aliosmanunaldi.wusicapp.data.addRoom.AddRoomResponse

data class AddRoomPageViewState(val result: AddRoomResponse?) {

    fun isAddButtonVisible(): Int = if (result?.success == true) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
