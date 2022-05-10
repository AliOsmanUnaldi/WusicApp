package com.aliosmanunaldi.wusicapp

import android.app.Application
import com.aliosmanunaldi.wusicapp.data.login.generateApi

class WusicAppAplication : Application() {

    override fun onCreate() {
        super.onCreate()

        generateApi(this)
    }
}