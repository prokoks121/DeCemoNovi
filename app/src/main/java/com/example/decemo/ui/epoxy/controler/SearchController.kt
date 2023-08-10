package com.example.decemo.ui.epoxy.controler

import android.content.Context
import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.example.decemo.model.Bar
import com.example.decemo.model.BarEvent
import com.example.decemo.model.BarType
import com.example.decemo.ui.epoxy.controler.listeners.SearchControllerListener
import com.example.decemo.ui.epoxy.model.BarTypeEpoxyViewModel_
import com.example.decemo.ui.epoxy.model.EventEpoxyViewModel_
import com.example.decemo.ui.epoxy.model.barEpoxyView
import com.example.decemo.ui.epoxy.model.searchEpoxyView
import com.example.decemo.ui.epoxy.model.textView

class SearchController(private val context: Context, private val listener: SearchControllerListener) : EpoxyController() {

    private var selectedType = 0

    private fun onSelectType(selectType: Int) {
        selectedType = selectType
        requestModelBuild()
    }

    private var bars: List<Bar> = listOf()
    private var events: List<BarEvent> = listOf()
    private var barTypes: List<BarType> = listOf()

    fun setBars(bars: List<Bar>) {
        this.bars = bars.toMutableList()
        requestModelBuild()
    }

    fun setEvents(events: List<BarEvent>) {
        this.events = events.toMutableList()
        requestModelBuild()
    }

    fun setBarTypes(barTypes: List<BarType>) {
        this.barTypes = barTypes.toMutableList()
        requestModelBuild()
    }

    override fun buildModels() {
        searchEpoxyView {
            id("search")
            onClick {
                listener.onSearchClick(it)
            }
        }

        val itemModels = events.mapIndexed { index, eventDos ->
            EventEpoxyViewModel_()
                .id("event-$index")
                .event(eventDos.events.first())
                .context(context)
                .onTouch { _ ->
                    listener.onEventClick(events, index)
                }
        }

        itemModels.let {
            carousel {
                id("events")
                models(it)
            }
        }

        val barTypeItems = barTypes.mapIndexed { index, barTypeDto ->
            val barType = BarTypeEpoxyViewModel_()
                .id("bar-types-$index")
                .barType(barTypeDto)
                .context(context)
                //TODO napraviti model koji ima bartype i status
                .myListener(View.OnClickListener {
                    onSelectType(index)
                    listener.onBarTypeClick(barTypeDto)
                })
            if (index == selectedType) {
                barType.status(true)
            } else {
                barType.status(false)
            }
            barType
        }

        barTypeItems.let {
            carousel {
                id("bar-types")
                models(it)
            }
        }

        textView {
            id("bar-text")
            text("Lokali")
        }

        bars.forEachIndexed { index, barDto ->
            barEpoxyView {
                id("bar-$index")
                bar(barDto)
                context(context)
                myListener { _ ->
                    listener.onBarClick(barDto)
                }
            }
        }
    }
}