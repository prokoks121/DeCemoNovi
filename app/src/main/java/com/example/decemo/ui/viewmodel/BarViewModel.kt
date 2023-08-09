package com.example.decemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.model.Bar
import com.example.decemo.repository.Repository
import kotlinx.coroutines.launch

class BarViewModel(repository: Repository) : BaseViewModel(repository) {

    lateinit var goToReservation: (Long?) -> Unit

    var barId: Long? = null

    private val _bar by lazy { MutableLiveData<Bar>() }
    val bar: LiveData<Bar>
        get() = _bar

    private fun getBar(barId: Long) {
        viewModelScope.launch {
            repository.getBar(barId).onSuccess {
                _bar.value = it
            }.onFailure {
                showErrorDialog(it.message ?: "Doslo je do greske prilikom dovlacenja barova.")
            }
        }
    }

    fun onReservationClick() {
        goToReservation(barId)
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