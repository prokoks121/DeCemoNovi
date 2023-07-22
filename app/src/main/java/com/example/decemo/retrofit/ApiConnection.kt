package com.example.decemo.retrofit


import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.dto.BarEvent
import com.example.decemo.retrofit.dto.BarTypeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiConnection {
    @GET("api/v1/explorer/bars")
    suspend fun getAllBars(): Response<MutableList<BarDto>>

    @GET("api/v1/explorer/bars")
    suspend fun getFilteredBarsByType(@Query("bartTypes") filterTypes: List<Long>): Response<MutableList<BarDto>>

    @GET("api/v1/explorer/bar/types")
    suspend fun getAllBarTypes(): Response<MutableList<BarTypeDto>>

    @GET("api/v1/explorer/events")
    suspend fun getListOfEvents(): Response<MutableList<BarEvent>>

    @GET("api/v1/explorer/bar/find/{filter}")
    suspend fun getFilteredBars(@Path("filter") filter: String): Response<MutableList<BarDto>>

    @GET("api/v1/explorer/bar/{id}")
    suspend fun getBar(@Path("id") id: Long): Response<BarDto>
}