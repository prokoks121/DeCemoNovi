package com.example.decemo.ui.epoxy.controler

import android.content.Context
import android.view.View
import com.airbnb.epoxy.Typed3EpoxyController
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

class SearchController(private val context: Context, private val listener: SearchControllerListener) :
    Typed3EpoxyController<List<Bar>?, List<BarEvent>?, List<BarType>?>() {

    override fun buildModels(bars: List<Bar>?, events: List<BarEvent>?, barTypes: List<BarType>?) {
        searchEpoxyView {
            id("search")
            onClick {
                listener.onSearchClick(it)
            }
        }

        val itemModels = events?.mapIndexed { index, eventDos ->
            EventEpoxyViewModel_()
                .id("event-$index")
                .event(eventDos.events.first())
                .context(context)
                .onTouch { _ ->
                    listener.onEventClick(events, index)
                }
        }

        itemModels?.let {
            carousel {
                id("events")
                models(it)
            }
        }

        val barTypeItems = barTypes?.mapIndexed { index, barTypeDto ->
            BarTypeEpoxyViewModel_()
                .id("bar-types-$index")
                .barType(barTypeDto)
                .context(context)
                //TODO napraviti model koji ima bartype i status
                .status(true)
                .myListener(View.OnClickListener {
                    listener.onBarTypeClick(barTypeDto)
                })
        }

        barTypeItems?.let {
            carousel {
                id("bar-types")
                models(it)
            }
        }

        textView {
            id("bar-text")
            text("Lokali")
        }

        bars?.forEachIndexed { index, barDto ->
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