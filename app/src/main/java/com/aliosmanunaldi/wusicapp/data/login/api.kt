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

    val clientBuilder = OkHttpClient.Builder()
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    clientBuilder.addInterceptor(loggingInterceptor)

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            clientBuilder
                .addNetworkInterceptor(loggingInterceptor)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    _api = retrofit.create(WusicApi::class.java)
}