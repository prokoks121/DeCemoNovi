package com.example.decemo.ui.epoxy.controler

import android.content.Context
import android.util.Log
import android.view.View
import com.airbnb.epoxy.*
import com.example.decemo.ui.epoxy.model.*
import com.example.decemo.model.Dogadjaj
import com.example.decemo.model.Lokal
import com.example.decemo.model.VrstaLokala


class PretragaController(val context: Context): EpoxyController() {



    var listaDogadjaj:ArrayList<Dogadjaj> = arrayListOf()
        set(value) {
            field = value
            requestModelBuild()
        }
    var listaVrteLokala: ArrayList<VrstaLokala> = arrayListOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    var listaLokala:ArrayList<Lokal> = arrayListOf()
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

        val vrsteLokalaItems = listaVrteLokala.map {
            VrstaLokalaViewModel_()
                    .id(it.id)
                    .vrstaLokala(it)
                    .myListener(View.OnClickListener {view->
                      //  changeStatus(it.id)
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
            }
        }



    }



    private fun changeStatus(id:Int){

       for (i in listaVrteLokala.indices){
           listaVrteLokala[i].status = listaVrteLokala[i].id == id
       }
        requestModelBuild()
    }


}