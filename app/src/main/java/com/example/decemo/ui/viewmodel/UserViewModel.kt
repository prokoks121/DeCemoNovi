package com.example.decemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.model.Reservation
import com.example.decemo.model.User
import com.example.decemo.repository.Repository
import kotlinx.coroutines.launch

class UserViewModel(repository: Repository) : BaseViewModel(repository) {
    lateinit var goToLogin: () -> Unit
    lateinit var goToReservationUpdate: (Long) -> Unit

    private val _user by lazy { MutableLiveData<User>() }
    val user: LiveData<User>
        get() = _user

    private val _reservations by lazy { MutableLiveData<List<Reservation>>() }
    val reservations: LiveData<List<Reservation>>
        get() = _reservations

    private fun getReservations() {
        viewModelScope.launch {
            repository.getReservations().onSuccess {
                _reservations.value = it
            }.onFailure {
                showErrorBox(it.message ?: "Doslo je do greske prilikom prikupljanja rezervacija.")
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            repository.getUser().onSuccess {
                _user.value = it
            }.onFailure {
                goToLogin()
            }
        }
    }

    fun onReservationClick(id: Long) {
        goToReservationUpdate(id)
    }

    fun onLogoutClick() {
        repository.logoutUser()
        goToLogin()
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getUser()
        getReservations()
    }
}