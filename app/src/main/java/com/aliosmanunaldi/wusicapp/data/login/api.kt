package com.aliosmanunaldi.wusicapp.data.login

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private var _api: WusicApi? = null

// Accessing this will crash if done before calling generate()
val api get() = _api!!

const val BASE_URL = "https://wusicapp.herokuapp.com"

fun generateApi(context: Context) {
    if (_api != null) return

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    _api = retrofit.create(WusicApi::class.java)
}