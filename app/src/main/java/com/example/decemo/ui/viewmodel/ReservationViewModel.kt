package com.example.decemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.repository.Repository
import com.example.decemo.retrofit.dto.BarDto
import kotlinx.coroutines.launch

class ReservationViewModel(repository: Repository) : BaseViewModel(repository) {

    var barId: Long? = null

    private val _bar by lazy { MutableLiveData<BarDto>() }
    val bar: LiveData<BarDto>
        get() = _bar

    fun onSubmitClick(date: String, numOfPlaces: Int) {
        //TODO
    }

    private fun getBar(barId: Long) {
        viewModelScope.launch {
            repository.getBar(barId).onSuccess {
                _bar.value = it
            }
        }
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getBar(barId!!)
    }
}