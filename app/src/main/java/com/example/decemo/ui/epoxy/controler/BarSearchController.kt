package com.example.decemo.ui.epoxy.controler

import android.content.Context
import com.airbnb.epoxy.TypedEpoxyController
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.ui.epoxy.model.barEpoxyView

class BarSearchController(val context: Context) : TypedEpoxyController<List<BarDto>?>() {

    private var onBarClick: (BarDto) -> Unit = {}

    fun setOnBarClick(onBarClick: (BarDto) -> Unit = {}) {
        this.onBarClick = onBarClick
    }

    override fun buildModels(data: List<BarDto>?) {
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