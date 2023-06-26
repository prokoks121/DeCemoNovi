package com.example.decemo.ui.viewmodel

import android.provider.Settings.Global
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.repository.Repository
import com.example.decemo.retrofit.dto.BarDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : BaseViewModel(repository) {
    private val _bars by lazy { MutableLiveData<List<BarDto>>() }
    val bars: LiveData<List<BarDto>>
        get() = _bars

    private fun getAllBars() {
        viewModelScope.launch {
            repository.getAllBars().onSuccess {
                _bars.value = it
            }.onFailure {
                it
            }
        }
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getAllBars()
    }
}