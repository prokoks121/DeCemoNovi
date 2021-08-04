package com.example.decemo.ui.epoxy.controler

import android.content.Context
import android.util.Log
import android.view.View
import com.airbnb.epoxy.*
import com.example.decemo.R
import com.example.decemo.model.Lokal
import com.example.decemo.ui.epoxy.model.*

class LokalController(val callBack: CallBack,val context: Context) : EpoxyController(), RadnoVremeModelGroup.callBack{

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

     var showAll:Boolean = false
     override fun onClick() {
         showAll = !showAll
         requestModelBuild()
         Log.d("Provera","Provera")
     }
    override fun buildModels() {

       logoLokalaView {
           id("Logo")
           img(lokal?.slika)
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
                .context(context)
                .lat(lokal!!.lat)
                .lon(lokal!!.long)
                .ime(lokal!!.ime)

        map.addIf(provera,this)


        var i = 0
        val galerija = lokal!!.galerija.map {
            GalerijaViewModel_().id(i++)
                    .context(context)
                    .url(it)
        } as ArrayList



    val model = CarouselModel_()
        .id("Galerija")
        .models(galerija)
        model.addIf(!provera,this)

      /*  radnoVremeView {
            id("RadnoVreme")
            list(lokal?.radno)
            callBack(this@LokalController)
            showAll(showAll)
        }*/
    val data:RadnoVremeModelGroup.data = RadnoVremeModelGroup.data(this@LokalController,lokal?.radno,showAll)
      add(RadnoVremeModelGroup(data))

    lokalUslugeView {
        id("Adresa")
        type(lokal?.adresa)
        icon(R.drawable.ic_maps_and_flags)
    }
        lokalUslugeView {
            id("Telefon")
            type(lokal?.telefon)
            icon(R.drawable.telefon)
        }

        var j = 0
        lokal?.let {
            it.usluge.forEach {
                lokalUslugeView {
                    id(j++)
                    type(it)
                    icon(R.drawable.ic_check)

                }
            }
        }


    }




    interface CallBack{
        fun hide(view:View)
    }
}