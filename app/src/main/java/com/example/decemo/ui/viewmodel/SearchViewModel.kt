package com.example.decemo.ui.viewmodel

import android.widget.AutoCompleteTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.model.Bar
import com.example.decemo.model.BarEvent
import com.example.decemo.model.BarType
import com.example.decemo.repository.Repository
import kotlinx.coroutines.launch

class SearchViewModel(repository: Repository) : BaseViewModel(repository) {
    lateinit var goToBar: (Long) -> Unit
    lateinit var goToBarSearch: () -> Unit
    lateinit var goToStory: (Pair<List<BarEvent>, Int>) -> Unit
    private lateinit var selectedBarType: BarType

    private val _bars: MutableLiveData<List<Bar>> by lazy { MutableLiveData<List<Bar>>() }
    val bars: LiveData<List<Bar>>
        get() = _bars

    private val _barTypes: MutableLiveData<List<BarType>> by lazy { MutableLiveData<List<BarType>>() }
    val barTypes: LiveData<List<BarType>>
        get() = _barTypes

    private val _events: MutableLiveData<List<BarEvent>> by lazy { MutableLiveData<List<BarEvent>>() }
    val events: LiveData<List<BarEvent>>
        get() = _events

    private fun getBars(barType: BarType) {
        viewModelScope.launch {
            repository.getFilteredBarsByType(listOf(barType.id)).onSuccess {
                _bars.value = it
            }.onFailure {
                showErrorBox(it.message ?: "Doslo je do greske prilikom prikupljanja barova.")
            }
        }
    }

    private fun getBareTypes() {
        viewModelScope.launch {
            repository.getAllBarTypes().onSuccess {
                _barTypes.value = it
                selectedBarType = it.first()
                getInitialBars()
            }.onFailure {
                showErrorBox(it.message ?: "Doslo je do greske prilikom prikupljanja barova.")
            }
        }
    }

    private suspend fun getInitialBars() {
        repository.getFilteredBarsByType(listOf(selectedBarType.id)).onSuccess {
            _bars.value = it
        }.onFailure {
            showErrorBox(it.message ?: "Doslo je do greske prilikom prikupljanja barova.")
        }
    }

    private fun getEvents() {
        viewModelScope.launch {
            repository.getAllEvents().onSuccess {
                _events.value = it
            }.onFailure {
                showErrorBox(it.message ?: "Doslo je do greske prilikom prikupljanja dogadjaja.")
            }
        }
    }

    fun onRefresh() {
        getBars(selectedBarType)
        getEvents()
    }

    fun onBarClick(bar: Bar) {
        goToBar(bar.id)
    }

    fun onEventClick(events: List<BarEvent>, position: Int) {
        goToStory(Pair(events, position))
    }

    fun onSearchClick(search: AutoCompleteTextView) {
        goToBarSearch()
    }

    fun onBarTypeClick(barType: BarType) {
        selectedBarType = barType
        getBars(barType)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getBareTypes()
        getEvents()
    }
}