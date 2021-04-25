package com.example.decemo.ui.epoxy.controler

import android.R
import android.content.Context
import android.view.View
import com.airbnb.epoxy.*
import com.example.decemo.model.Lokal
import com.example.decemo.ui.epoxy.model.*
import kotlin.properties.Delegates

class LokalController(val callBack: CallBack,val context: Context) : EpoxyController() {

    var provera = true
        set(value) {
            field = value
            requestModelBuild()
        }
    var lokal:Lokal? = null
    set(value) {
        field = value
        requestModelBuild()
    }

    override fun buildModels() {

       logoLokalaView {
           id("Logo")
           img(lokal!!.slika)
           context(context)

       }

        imeLokalaView {
            id("ime lokala")
            imeLokala(lokal!!.ime)
            vrstaLokala(lokal!!.vrsta)

        }
        MeniViewModel_()
                .id("meni")
                .myListener(View.OnClickListener {
              callBack.hide(it)
            }).addTo(this)

        val map = MapBoxViewModel_ ()
                .id("map box")
                .context(context!!)
                .lat(lokal!!.lat)
                .lon(lokal!!.long)
                .ime(lokal!!.ime)

        map.addIf(provera,this)

        var i = 0
        val galerija = lokal!!.galerija.map {
            GalerijaViewModel_().id(i++)
                    .context(context!!)
                    .url(it)
        } as ArrayList

    val model = CarouselModel_()
        .id("Galerija")
        .models(galerija)
        model.addIf(!provera,this)
    }

    interface CallBack{
        fun hide(view:View)
    }
}