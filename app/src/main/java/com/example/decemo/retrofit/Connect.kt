package com.example.decemo.retrofit

import com.example.decemo.base.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Connect {
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .create()
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    fun provideOkHttpClient(): OkHttpClient {
//    https://medium.com/@harmittaa/retrofit-2-6-0-with-koin-and-coroutines-4ff45a4792fc
//    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
        return OkHttpClient().newBuilder().build()

    }

    fun provideForecastApi(retrofit: Retrofit): ApiConnection = retrofit.create(ApiConnection::class.java)
}