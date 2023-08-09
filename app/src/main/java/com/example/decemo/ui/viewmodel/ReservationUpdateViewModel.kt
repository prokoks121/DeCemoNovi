package com.example.decemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.repository.Repository
import com.example.decemo.retrofit.dto.Reservation
import com.example.decemo.retrofit.request.ReservationRequest
import kotlinx.coroutines.launch

class ReservationUpdateViewModel(repository: Repository) : BaseViewModel(repository) {
    var reservationId: Long? = null

    private val _reservation by lazy { MutableLiveData<Reservation>() }
    val reservation: LiveData<Reservation>
        get() = _reservation

    fun onUpdateClick(date: String, numOfPlaces: Int) {
        if (_reservation.value == null) {
            return
        }
        reservationUpdate(date, numOfPlaces)
    }

    fun onDeleteClick() {
        deleteReservation()
    }

    private fun reservationUpdate(date: String, numOfPlaces: Int) {
        viewModelScope.launch {
            val reservationRequest = ReservationRequest(numOfPlaces, date, reservation.value?.barId!!)
            repository.updateReservation(reservationId!!, reservationRequest).onSuccess {
                _reservation.value = it
                showSuccessBox("Uspesno ste azurirali rezervaciju.")
            }.onFailure {
                showErrorBox("Doslo je do gresko prilikom izmene rezervacije.")
            }
        }
    }

    private fun deleteReservation() {
        viewModelScope.launch {
            repository.deleteReservation(reservationId!!).onSuccess {
                goBack()
            }.onFailure {
                showErrorBox(it.message ?: "Doslo je do greske prilikom brisanja rezervacije.")
            }
        }
    }

    private fun getReservation(reservationId: Long) {
        viewModelScope.launch {
            repository.getReservation(reservationId).onSuccess {
                _reservation.value = it
            }.onFailure {
                showErrorDialog(it.message ?: "Doslo je do greske prilikom preuzimanja rezervacije.")
            }
        }
    }

    override fun onViewCreated() {
        super.onViewCreated()
        if (reservationId ==null){
            goBack()
            return
        }
        getReservation(reservationId!!)
    }
}