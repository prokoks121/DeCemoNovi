package com.example.decemo.ui.epoxy.controler.listeners

import android.widget.AutoCompleteTextView
import com.example.decemo.model.Bar
import com.example.decemo.model.BarEvent
import com.example.decemo.model.BarType

interface SearchControllerListener {
    fun onBarTypeClick(barType: BarType)
    fun onBarClick(bar: Bar)
    fun onEventClick(events: List<BarEvent>, position: Int)
    fun onSearchClick(search: AutoCompleteTextView)
}