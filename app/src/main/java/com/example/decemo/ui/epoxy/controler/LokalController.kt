package com.example.decemo.ui.epoxy.controler

import android.R
import android.content.Context
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.example.decemo.model.Lokal
import com.example.decemo.ui.epoxy.model.*

class LokalController : Typed2EpoxyController<Lokal, Context>() {
    override fun buildModels(lokal: Lokal?, context:Context?) {

       logoLokalaView {
           id("Logo")
           img(lokal!!.slika)
           context(context!!)

       }

        imeLokalaView {
            id("ime lokala")
            imeLokala(lokal!!.ime)
            vrstaLokala(lokal.vrsta)

        }

        mapBoxView {
            id("map box")
            context(context!!)
            lat(lokal!!.lat)
            lon(lokal.long)
            ime(lokal.ime)
        }
        var i = 0
        val galerija = lokal!!.galerija.map {
            GalerijaViewModel_().id(i++)
                    .context(context!!)
                    .url(it)
        } as ArrayList

    val model = CarouselModel_()
        .id("Galerija")
        .models(galerija)

        model.addTo(this)
        model.hide()




    }

}