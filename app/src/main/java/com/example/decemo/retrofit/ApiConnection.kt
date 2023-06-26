package com.example.decemo.retrofit


import com.example.decemo.model.Dogadjaj
import com.example.decemo.model.Lokal
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.dto.EventDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiConnection {
    @GET("api/v1/bars")
    suspend fun getListOfLocals(): Response<MutableList<BarDto>>

    @GET("api-dogadjaji.php")
    suspend fun getListOfEvents(): Call<MutableList<EventDto>>
}