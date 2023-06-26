//package com.example.decemo.ui.epoxy.controler
//
//import android.view.View
//import android.widget.AutoCompleteTextView
//import com.airbnb.epoxy.TypedEpoxyController
//import com.airbnb.epoxy.carousel
//import com.example.decemo.model.Dogadjaj
//import com.example.decemo.model.Lokal
//import com.example.decemo.ui.epoxy.model.DogadjaliViewModel_
//import com.example.decemo.ui.epoxy.model.VrstaLokalaViewModel_
//import com.example.decemo.ui.epoxy.model.lokaliView
//import com.example.decemo.ui.epoxy.model.pretragaView
//import com.example.decemo.ui.epoxy.model.textView
//import com.example.decemo.ui.view.dataForController
//
//
//class PretragaController : TypedEpoxyController<dataForController>() {
//    override fun buildModels(data: dataForController?) {
//        pretragaView {
//            id("Pretraga")
//            onClick {
//                data?.callBack?.onSearchClick(it)
//            }
//        }
//        val itemModels = ArrayList<DogadjaliViewModel_>()
//        data?.let {
//            for (i in 0 until it.dogadjaji.size) {
//                itemModels.add(DogadjaliViewModel_()
//                    .id(i)
//                    .dogadjaj(it.dogadjaji[i][0])
//                    .context(data.context)
//                    .onTouch(View.OnClickListener { o ->
//                        data.callBack.onDogadjajTouch(it.dogadjaji[i][0], i)
//                    })
//                )
//            }
//        }
//        itemModels.let {
//            carousel {
//                id("Dogadjaji")
//                models(it)
//            }
//        }
//
//
//        val vrsteLokalaItems = data?.let {
//            it.listaVrteLokala.map { lokal ->
//                VrstaLokalaViewModel_()
//                    .id(lokal.id)
//                    .vrstaLokala(lokal)
//                    .myListener(View.OnClickListener {
//                        data.callBack.click(lokal.id, lokal.vrsta)
//                    })
//            }
//        }
//        vrsteLokalaItems?.let {
//            carousel {
//                id("vrsteLokala")
//                models(it)
//            }
//        }
//
//        textView {
//            id("id_lokali")
//            text("Lokali")
//        }
//
//        data?.let {
//            it.lokali.forEach {
//                lokaliView {
//                    id(it.id)
//                    lokal(it)
//                    context(data.context)
//                    myListener(View.OnClickListener { view ->
//                        data.callBack.onLokalClick(it)
//                    })
//                }
//            }
//        }
//    }
//
//    interface changeStatus {
//        fun click(id: Int, vrsta: String)
//        fun onLokalClick(lokal: Lokal)
//        fun onDogadjajTouch(dogadjaj: Dogadjaj, position: Int)
//        fun onSearchClick(search: AutoCompleteTextView)
//    }
//}