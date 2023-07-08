package com.example.decemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.model.MapFilter
import com.example.decemo.repository.Repository
import com.example.decemo.retrofit.dto.BarDto
import kotlinx.coroutines.launch

class HomeViewModel(repository: Repository) : BaseViewModel(repository) {
    private val _bars by lazy { MutableLiveData<List<BarDto>>() }
    val bars: LiveData<List<BarDto>>
        get() = _bars

    var barTypes = listOf<MapFilter>()

    lateinit var goToBar: (Long) -> Unit

    fun onBarClick(barId: Long) {
        goToBar(barId)
    }

    private fun getAllBars() {
        viewModelScope.launch {
            val filterTypes: List<Long> = barTypes.filter { mapFilter -> mapFilter.status }.map { mapFilter -> mapFilter.id }

            if (filterTypes.isEmpty()) {
                repository.getAllBars().onSuccess {
                    _bars.value = it
                }.onFailure {
                    //TODO prikaz greske, neki popup
                    it
                }
            } else {
                repository.getFilteredBarsByType(filterTypes).onSuccess {
                    _bars.value = it
                }.onFailure {
                    //TODO prikaz greske, neki popup
                    it
                }
            }

        }
    }

    private fun getAllBarTypes() {
        viewModelScope.launch {
            repository.getAllBarTypes().onSuccess { barType ->
                barTypes = barType.map { MapFilter(type = it.type, status = true, id = it.id) }.toList()
            }.onFailure {
                //TODO prikaz greske, neki popup
                it
            }
        }
    }

    fun updateMapFilterStatus(filter: MapFilter, status: Boolean) {
        val barType = barTypes.find { mapFilter -> mapFilter.id == filter.id }
        barType!!.status = status
        getAllBars()
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getAllBars()
        if (barTypes.isEmpty()) {
            getAllBarTypes()
        }
    }
}