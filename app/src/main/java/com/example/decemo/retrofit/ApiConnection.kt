package com.example.decemo.retrofit


import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.dto.BarEvent
import com.example.decemo.retrofit.dto.BarTypeDto
import com.example.decemo.retrofit.dto.JwtToken
import com.example.decemo.retrofit.dto.LoginRequest
import com.example.decemo.retrofit.dto.Reservation
import com.example.decemo.retrofit.dto.UserDto
import com.example.decemo.retrofit.request.ReservationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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

    @POST("api/v1/user/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<JwtToken>

    @GET("api/v1/user/get")
    suspend fun getUser(@Header("Authorization") authHeader: String): Response<UserDto>

    @GET("api/v1/reservation/reservations")
    suspend fun getReservations(@Header("Authorization") authHeader: String): Response<List<Reservation>>

    @GET("api/v1/reservation/{id}")
    suspend fun getReservation(@Header("Authorization") authHeader: String, @Path("id") id:Long): Response<Reservation>

    @DELETE("api/v1/reservation/reserve/{id}")
    suspend fun deleteReservation(@Header("Authorization") authHeader: String, @Path("id") id: Long): Response<Unit>

    @POST("api/v1/reservation/{id}")
    suspend fun updateReservation(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Long,
        @Body reservationRequest: ReservationRequest
    ): Response<Reservation>

    @POST("api/v1/reservation/reserve")
    suspend fun reserve(@Header("Authorization") authHeader: String, @Body reservationRequest: ReservationRequest): Response<Reservation>
}