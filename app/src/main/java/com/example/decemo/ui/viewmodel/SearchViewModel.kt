package com.example.decemo.ui.viewmodel

import android.widget.AutoCompleteTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.repository.Repository
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.dto.BarEvent
import com.example.decemo.retrofit.dto.BarTypeDto
import kotlinx.coroutines.launch

class SearchViewModel(repository: Repository) : BaseViewModel(repository) {

    lateinit var goToBar: (Long) -> Unit

    lateinit var goToBarSearch: () -> Unit
    lateinit var goToStory: (Pair<List<BarEvent>, Int>) -> Unit

    private lateinit var selectedBarType: BarTypeDto

    private val _bars: MutableLiveData<List<BarDto>> by lazy { MutableLiveData<List<BarDto>>() }
    val bars: LiveData<List<BarDto>>
        get() = _bars

    private val _barTypes: MutableLiveData<List<BarTypeDto>> by lazy { MutableLiveData<List<BarTypeDto>>() }
    val barTypes: LiveData<List<BarTypeDto>>
        get() = _barTypes

    private val _events: MutableLiveData<List<BarEvent>> by lazy { MutableLiveData<List<BarEvent>>() }
    val events: LiveData<List<BarEvent>>
        get() = _events

    private fun getBars(barType: BarTypeDto) {
        viewModelScope.launch {
            repository.getFilteredBarsByType(listOf(barType.id)).onSuccess {
                _bars.value = it
            }
        }
    }

    private fun getBareTypes() {
        viewModelScope.launch {
            repository.getAllBarTypes().onSuccess {
                _barTypes.value = it
                selectedBarType = it.first()
                getInitialBars()
            }
        }
    }

    private suspend fun getInitialBars() {
        repository.getFilteredBarsByType(listOf(selectedBarType.id)).onSuccess {
            _bars.value = it
        }
    }

    private fun getEvents() {
        viewModelScope.launch {
            repository.getAllEvents().onSuccess {
                _events.value = it
            }
        }
    }

    fun onRefresh() {
        getBars(selectedBarType)
        getEvents()
    }

    fun onBarClick(bar: BarDto) {
        goToBar(bar.id)
    }

    fun onEventClick(events: List<BarEvent>, position: Int) {
        //        val action = SearchFragmentDirections.actionSearchToStoryFragment(
//            StoryFragment.Data(data.dogadjaji, position)
//        )
//        requireView().findNavController().navigate(action)
        goToStory(Pair(events, position))
    }

    fun onSearchClick(search: AutoCompleteTextView) {
        //        val ser = "search"
//        val extras = FragmentNavigatorExtras(
//            search to ser
//        )
//        val action = SearchFragmentDirections.actionSearchToSearchLokaliFragment(uri = ser)
//        findNavController().navigate(action, extras)
        goToBarSearch()
    }

    fun onBarTypeClick(barType: BarTypeDto) {
        selectedBarType = barType
        getBars(barType)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getBareTypes()
        getEvents()
    }
}