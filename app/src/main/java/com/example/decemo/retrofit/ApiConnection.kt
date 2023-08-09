package com.example.decemo.retrofit


import com.example.decemo.model.Bar
import com.example.decemo.model.BarEvent
import com.example.decemo.model.BarType
import com.example.decemo.model.JwtToken
import com.example.decemo.model.LoginRequest
import com.example.decemo.model.RegistrationRequest
import com.example.decemo.model.Reservation
import com.example.decemo.model.User
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
    suspend fun getAllBars(): Response<MutableList<Bar>>

    @GET("api/v1/explorer/bars")
    suspend fun getFilteredBarsByType(@Query("bartTypes") filterTypes: List<Long>): Response<MutableList<Bar>>

    @GET("api/v1/explorer/bar/types")
    suspend fun getAllBarTypes(): Response<MutableList<BarType>>

    @GET("api/v1/explorer/events")
    suspend fun getListOfEvents(): Response<MutableList<BarEvent>>

    @GET("api/v1/explorer/bar/find/{filter}")
    suspend fun getFilteredBars(@Path("filter") filter: String): Response<MutableList<Bar>>

    @GET("api/v1/explorer/bar/{id}")
    suspend fun getBar(@Path("id") id: Long): Response<Bar>

    @POST("api/v1/user/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<JwtToken>
    @POST("api/v1/user/registration")
    suspend fun registration(@Body registrationRequest: RegistrationRequest): Response<JwtToken>
    @GET("api/v1/user/get")
    suspend fun getUser(@Header("Authorization") authHeader: String): Response<User>

    @GET("api/v1/reservation/reservations")
    suspend fun getReservations(@Header("Authorization") authHeader: String): Response<List<Reservation>>

    @GET("api/v1/reservation/{id}")
    suspend fun getReservation(@Header("Authorization") authHeader: String, @Path("id") id: Long): Response<Reservation>

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