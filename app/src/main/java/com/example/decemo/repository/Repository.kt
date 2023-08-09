package com.example.decemo.repository

import com.example.decemo.model.Bar
import com.example.decemo.model.BarEvent
import com.example.decemo.model.BarType
import com.example.decemo.model.JwtToken
import com.example.decemo.model.Reservation
import com.example.decemo.model.User
import com.example.decemo.retrofit.request.ReservationRequest

interface Repository {
    suspend fun getAllBars(): Result<MutableList<Bar>>
    suspend fun getFilteredBarsByType(filterTypes: List<Long>): Result<MutableList<Bar>>
    suspend fun getAllBarTypes(): Result<MutableList<BarType>>
    suspend fun getBar(barId: Long): Result<Bar>
    suspend fun getFilteredBars(filter: String): Result<MutableList<Bar>>
    suspend fun getAllEvents(): Result<MutableList<BarEvent>>
    suspend fun loginUser(email: String, password: String): Result<JwtToken>
    suspend fun registrationUser(fullName: String, email: String, password: String): Result<JwtToken>
    suspend fun getUser(): Result<User>
    fun logoutUser()
    suspend fun getReservations(): Result<List<Reservation>>
    suspend fun getReservation(reservationId: Long): Result<Reservation>
    suspend fun deleteReservation(id: Long): Result<Unit>
    suspend fun updateReservation(id: Long, reservationRequest: ReservationRequest): Result<Reservation>
    suspend fun reserve(reservationRequest: ReservationRequest): Result<Reservation>
}