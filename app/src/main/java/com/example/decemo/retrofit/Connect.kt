package com.example.decemo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Connect {
    const val url = "https://bekmen.rs/api/"
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
    }
    val retrofitApiInterface by lazy {
        retrofit.build()
                .create(ApiConntect::class.java)
    }

}