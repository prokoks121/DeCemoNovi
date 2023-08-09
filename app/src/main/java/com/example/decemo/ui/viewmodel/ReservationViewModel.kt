package com.example.decemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.repository.Repository
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.request.ReservationRequest
import kotlinx.coroutines.launch

class ReservationViewModel(repository: Repository) : BaseViewModel(repository) {
    var barId: Long? = null

    private val _bar by lazy { MutableLiveData<BarDto>() }
    val bar: LiveData<BarDto>
        get() = _bar

    fun onSubmitClick(date: String, numOfPlaces: Int) {
        reserve(date, numOfPlaces)
    }

    private fun reserve(date: String, numOfPlaces: Int) {
        viewModelScope.launch {
            val reservationRequest = ReservationRequest(numOfPlaces, date, barId!!)
            repository.reserve(reservationRequest).onSuccess {
                showSuccessBox("Uspesno ste kreirali rezervaciju.")
            }.onFailure {
                showSuccessBox(it.message ?: "Doslo je do greske prilikom kreiranja rezervacije")
            }
        }
    }

    private fun getBar(barId: Long) {
        viewModelScope.launch {
            repository.getBar(barId).onSuccess {
                _bar.value = it
            }.onFailure {
                showErrorDialog(it.message ?: "Doslo je do greske priliko dobovljanja bara.")
            }
        }
    }

    override fun onViewCreated() {
        super.onViewCreated()
        if (barId == null) {
            goBack()
            return
        }
        getBar(barId!!)
    }
}