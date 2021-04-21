package com.example.decemo.ui.epoxy.controler

import android.content.Context
import android.util.Log
import android.view.View
import com.airbnb.epoxy.*
import com.example.decemo.ui.epoxy.model.*
import com.example.decemo.model.Dogadjaj
import com.example.decemo.model.Lokal
import com.example.decemo.model.VrstaLokala


class PretragaController(val context: Context,val callback:changeStatus) : EpoxyController() {
    var listaDogadjaj: ArrayList<Dogadjaj> = arrayListOf()
        set(value) {
            field = value
            requestModelBuild()
        }
    var listaVrteLokala: ArrayList<VrstaLokala> = arrayListOf()
        set(value) {
            field = value
            requestModelBuild()
        }
    var listaLokala: ArrayList<Lokal> = arrayListOf()
        set(value) {
            field = value
            requestModelBuild()
        }
    override fun buildModels() {
        pretragaView {
            id("Pretraga")
        }
        val itemModels = listaDogadjaj.map { item ->
            DogadjaliViewModel_()
                    .id(item.id)
                    .dogadjaj(item)
                    .context(context)
        }
        carousel {
            id("Dogadjaji")
            models(itemModels)
        }
        val vrsteLokalaItems = listaVrteLokala.map {lokal->
            VrstaLokalaViewModel_()
                    .id(lokal.id)
                    .vrstaLokala(lokal)
                    .myListener(View.OnClickListener {
                      callback.click(lokal.id,lokal.vrsta)
                    })
        }
        carousel {
            id("vrsteLokala")
            models(vrsteLokalaItems)
        }
        textView {
            id("id_lokali")
            text("Lokali")
        }
        listaLokala.forEach {
            lokaliView {
                id(it.id)
                lokal(it)
                context(context)
                myListener(View.OnClickListener { view->
                    callback.onLokalClick(it)
                })
            }
        }
    }
   interface changeStatus {
      fun click(id:Int,vrsta:String)
       fun onLokalClick(lokal:Lokal)
   }

}