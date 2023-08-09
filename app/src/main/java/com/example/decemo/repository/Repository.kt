package com.example.decemo.repository

import com.example.decemo.retrofit.ApiConnection
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.dto.BarEvent
import com.example.decemo.retrofit.dto.BarTypeDto
import com.example.decemo.retrofit.dto.JwtToken
import com.example.decemo.retrofit.dto.LoginRequest
import com.example.decemo.retrofit.dto.Reservation
import com.example.decemo.retrofit.dto.UserDto
import com.example.decemo.retrofit.request.ReservationRequest
import com.example.decemo.token.TokenStorage

class Repository(private val connection: ApiConnection, private val tokenStorage: TokenStorage) {

    suspend fun getAllBars(): Result<MutableList<BarDto>> {
        runCatching {
            val response = connection.getAllBars()
            if (response.isSuccessful) {
                return Result.success(response.body() ?: mutableListOf())
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun getFilteredBarsByType(filterTypes: List<Long>): Result<MutableList<BarDto>> {
        runCatching {
            val response = connection.getFilteredBarsByType(filterTypes)
            if (response.isSuccessful) {
                return Result.success(response.body() ?: mutableListOf())
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun getAllBarTypes(): Result<MutableList<BarTypeDto>> {
        runCatching {
            val response = connection.getAllBarTypes()
            if (response.isSuccessful) {
                return Result.success(response.body() ?: mutableListOf())
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun getBar(barId: Long): Result<BarDto> {
        runCatching {
            val response = connection.getBar(barId)
            if (response.isSuccessful) {
                return Result.success(response.body()!!)
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun getFilteredBars(filter: String): Result<MutableList<BarDto>> {
        runCatching {
            val response = connection.getFilteredBars(filter)
            if (response.isSuccessful) {
                return Result.success(response.body() ?: mutableListOf())
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun getAllEvents(): Result<MutableList<BarEvent>> {
        runCatching {
            val response = connection.getListOfEvents()
            if (response.isSuccessful) {
                return Result.success(response.body()!!)
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun loginUser(email: String, password: String): Result<JwtToken> {
        runCatching {
            val response = connection.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                tokenStorage.putAccessToken(response.body()!!.token)
                tokenStorage.putRefreshToken(response.body()!!.refreshToken)
                return Result.success(response.body()!!)
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun getUser(): Result<UserDto> {
        runCatching {
            val token = "Bearer ${tokenStorage.getAccessToken()}"
            val response = connection.getUser(token)
            if (response.isSuccessful) {
                return Result.success(response.body()!!)
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    fun logoutUser() {
        tokenStorage.clearTokens()
    }

    suspend fun getReservations(): Result<List<Reservation>> {
        runCatching {
            val response = connection.getReservations(tokenStorage.getAccessToken() ?: "")
            if (response.isSuccessful) {
                return Result.success(response.body() ?: mutableListOf())
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun getReservation(reservationId: Long): Result<Reservation> {
        runCatching {
            val response = connection.getReservation(tokenStorage.getAccessToken() ?: "", reservationId)
            if (response.isSuccessful) {
                return Result.success(response.body()!!)
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun deleteReservation(id: Long): Result<Unit> {
        runCatching {
            val response = connection.deleteReservation(tokenStorage.getAccessToken() ?: "", id)
            if (response.isSuccessful) {
                return Result.success(Unit)
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun updateReservation(id: Long, reservationRequest: ReservationRequest): Result<Reservation> {
        runCatching {
            val response = connection.updateReservation(tokenStorage.getAccessToken() ?: "", id, reservationRequest)
            if (response.isSuccessful) {
                return Result.success(response.body()!!)
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }

    suspend fun reserve(reservationRequest: ReservationRequest): Result<Reservation> {
        runCatching {
            val response = connection.reserve(tokenStorage.getAccessToken() ?: "", reservationRequest)
            if (response.isSuccessful) {
                return Result.success(response.body()!!)
            }
            return Result.failure(Exception())
        }.onFailure {
            return Result.failure(it)
        }
        return Result.failure(Exception())
    }
}