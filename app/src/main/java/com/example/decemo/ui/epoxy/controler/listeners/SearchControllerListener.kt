package com.example.decemo.ui.epoxy.controler.listeners

import android.widget.AutoCompleteTextView
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.dto.BarEvent
import com.example.decemo.retrofit.dto.BarTypeDto
import com.example.decemo.retrofit.dto.EventDto

interface SearchControllerListener {
    fun onBarTypeClick(barType: BarTypeDto)
    fun onBarClick(bar: BarDto)
    fun onEventClick(events: List<BarEvent>, position: Int)
    fun onSearchClick(search: AutoCompleteTextView)
}