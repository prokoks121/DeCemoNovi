package com.example.decemo.repository

import com.example.decemo.retrofit.ApiConnection
import com.example.decemo.model.Bar
import com.example.decemo.model.BarEvent
import com.example.decemo.model.BarType
import com.example.decemo.model.JwtToken
import com.example.decemo.model.LoginRequest
import com.example.decemo.model.RegistrationRequest
import com.example.decemo.model.Reservation
import com.example.decemo.model.User
import com.example.decemo.retrofit.request.ReservationRequest
import com.example.decemo.token.TokenStorage

class ExternalRepositoryImpl(private val connection: ApiConnection, private val tokenStorage: TokenStorage) : Repository {

    override suspend fun getAllBars(): Result<MutableList<Bar>> {
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

    override suspend fun getFilteredBarsByType(filterTypes: List<Long>): Result<MutableList<Bar>> {
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

    override suspend fun getAllBarTypes(): Result<MutableList<BarType>> {
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

    override suspend fun getBar(barId: Long): Result<Bar> {
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

    override suspend fun getFilteredBars(filter: String): Result<MutableList<Bar>> {
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

    override suspend fun getAllEvents(): Result<MutableList<BarEvent>> {
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

    override suspend fun loginUser(email: String, password: String): Result<JwtToken> {
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

    override suspend fun registrationUser(fullName: String, email: String, password: String): Result<JwtToken> {
        runCatching {
            val registrationRequest = RegistrationRequest(email, password, fullName)
            val response = connection.registration(registrationRequest)
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

    override suspend fun getUser(): Result<User> {
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

    override fun logoutUser() {
        tokenStorage.clearTokens()
    }

    override suspend fun getReservations(): Result<List<Reservation>> {
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

    override suspend fun getReservation(reservationId: Long): Result<Reservation> {
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

    override suspend fun deleteReservation(id: Long): Result<Unit> {
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

    override suspend fun updateReservation(id: Long, reservationRequest: ReservationRequest): Result<Reservation> {
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

    override suspend fun reserve(reservationRequest: ReservationRequest): Result<Reservation> {
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