package com.example.decemo.ui.epoxy.controler

import android.content.Context
import com.airbnb.epoxy.TypedEpoxyController
import com.example.decemo.model.Bar
import com.example.decemo.ui.epoxy.model.barEpoxyView

class BarSearchController(val context: Context) : TypedEpoxyController<List<Bar>?>() {

    private var onBarClick: (Bar) -> Unit = {}

    fun setOnBarClick(onBarClick: (Bar) -> Unit = {}) {
        this.onBarClick = onBarClick
    }

    override fun buildModels(data: List<Bar>?) {
        data?.forEach {
            barEpoxyView {
                id(it.id)
                bar(it)
                context(context)
                myListener { _ ->
                    onBarClick(it)
                }
            }
        }
    }
}