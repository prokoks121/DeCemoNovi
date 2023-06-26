//package com.example.decemo.ui.epoxy.controler
//
//import android.content.Context
//import android.view.View
//import com.airbnb.epoxy.CarouselModel_
//import com.airbnb.epoxy.EpoxyController
//import com.example.decemo.R
//import com.example.decemo.model.Lokal
//import com.example.decemo.ui.epoxy.model.GalerijaViewModel_
//import com.example.decemo.ui.epoxy.model.MapBoxViewModel_
//import com.example.decemo.ui.epoxy.model.MeniViewModel_
//import com.example.decemo.ui.epoxy.model.RadnoVremeModelGroup
//import com.example.decemo.ui.epoxy.model.imeLokalaView
//import com.example.decemo.ui.epoxy.model.logoLokalaView
//import com.example.decemo.ui.epoxy.model.lokalUslugeView
//
//class LokalController(val callBack: CallBack, val context: Context) : EpoxyController(), RadnoVremeModelGroup.callBack {
//
//    var provera = true
//        set(value) {
//            field = value
//            requestModelBuild()
//        }
//    var lokal: Lokal? = null
//        set(value) {
//            field = value
//            requestModelBuild()
//        }
//
//    var showAll: Boolean = false
//    override fun onClick() {
//        showAll = !showAll
//        requestModelBuild()
//    }
//
//    override fun buildModels() {
//
//        logoLokalaView {
//            id("Logo")
//            img(lokal?.slika)
//            context(context)
//
//        }
//
//        imeLokalaView {
//            id("ime lokala")
//            imeLokala(lokal!!.ime)
//            vrstaLokala(lokal!!.vrsta)
//
//        }
//        MeniViewModel_()
//            .id("meni")
//            .check(provera)
//            .myListener(View.OnClickListener {
//                callBack.hide(it)
//            }).addTo(this)
//
//        val map = MapBoxViewModel_()
//            .id("map box")
//            .context(context)
//            .lat(lokal!!.lat)
//            .lon(lokal!!.long)
//            .ime(lokal!!.ime)
//
//        map.addIf(provera, this)
//
//
//        var i = 0
//        val galerija = lokal!!.galerija.map {
//            GalerijaViewModel_().id(i++)
//                .context(context)
//                .url(it)
//        } as ArrayList
//
//
//        val model = CarouselModel_()
//            .id("Galerija")
//            .models(galerija)
//            .hasFixedSize(true)
//        model.addIf(!provera, this)
//
//        /*  radnoVremeView {
//              id("RadnoVreme")
//              list(lokal?.radno)
//              callBack(this@LokalController)
//              showAll(showAll)
//          }*/
//        val data: RadnoVremeModelGroup.data = RadnoVremeModelGroup.data(this@LokalController, lokal?.radno, showAll)
//        add(RadnoVremeModelGroup(data))
//
//        lokalUslugeView {
//            id("Adresa")
//            type(lokal?.adresa)
//            icon(R.drawable.ic_maps_and_flags)
//        }
//        lokalUslugeView {
//            id("Telefon")
//            type(lokal?.telefon)
//            icon(R.drawable.telefon)
//        }
//
//        var j = 0
//        lokal?.let {
//            it.usluge.forEach {
//                lokalUslugeView {
//                    id(j++)
//                    type(it)
//                    icon(R.drawable.ic_check)
//
//                }
//            }
//        }
//
//
//    }
//
//
//    interface CallBack {
//        fun hide(view: View)
//    }
//}