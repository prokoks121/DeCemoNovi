package com.example.decemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.model.Bar
import com.example.decemo.repository.Repository
import kotlinx.coroutines.launch

class BarSearchViewModel(repository: Repository) : BaseViewModel(repository) {

    lateinit var goToBar: (Long) -> Unit

    private val _bars by lazy { MutableLiveData<List<Bar>>() }
    val bars: LiveData<List<Bar>>
        get() = _bars

    private fun getFilteredBars(filter: String? = null) {
        viewModelScope.launch {
            if (filter.isNullOrEmpty()) {
                repository.getAllBars().onSuccess {
                    _bars.value = it
                }.onFailure {
                    showErrorDialog(it.message ?: "Doslo je do greske prilikom dovlacenja barova.")
                }
            } else {
                repository.getFilteredBars(filter).onSuccess {
                    _bars.value = it
                }.onFailure {
                    showErrorDialog(it.message ?: "Doslo je do greske prilikom dovlacenja barova.")
                }
            }
        }
    }

    fun onBarClick(bar: Bar) {
        goToBar(bar.id)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getFilteredBars()
    }

    fun searchBar(filter: String) {
        getFilteredBars(filter)
    }
}