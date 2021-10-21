package com.example.decemo.ui.epoxy.controler

import android.content.Context
import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.example.decemo.model.Lokal
import com.example.decemo.ui.epoxy.model.lokaliView

class SearchLokaliController(val context: Context,val callBack:CallBack): EpoxyController() {

    var listaLokala: ArrayList<Lokal>? = null
        set(value) {
            field = value
            requestModelBuild()
        }
    override fun buildModels() {
        listaLokala?.forEach {
            lokaliView {
                id(it.id)
                lokal(it)
                context(context)
                myListener(View.OnClickListener { view->
                    callBack.onLokalClick(it)
                })
            }
        }
    }

    interface CallBack{
        fun onLokalClick(lokal:Lokal)
    }
}