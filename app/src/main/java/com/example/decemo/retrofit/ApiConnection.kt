package com.example.decemo.retrofit


import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.dto.BarTypeDto
import com.example.decemo.retrofit.dto.EventDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiConnection {
    @GET("api/v1/bars")
    suspend fun getAllBars(): Response<MutableList<BarDto>>

    @GET("api/v1/bars")
    suspend fun getFilteredBarsByType(@Query("bartTypes") filterTypes: List<Long>): Response<MutableList<BarDto>>

    @GET("api/v1/bar/types")
    suspend fun getAllBarTypes(): Response<MutableList<BarTypeDto>>

    @GET("api-dogadjaji.php")
    suspend fun getListOfEvents(): Call<MutableList<EventDto>>

    @GET("api/v1/bar/{id}")
    suspend fun getBar(@Path("id") id: Long): Response<BarDto>
}