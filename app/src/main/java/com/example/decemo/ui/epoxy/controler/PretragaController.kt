package com.example.decemo.ui.epoxy.controler

import android.view.View
import com.airbnb.epoxy.*
import com.example.decemo.model.Dogadjaj
import com.example.decemo.ui.epoxy.model.*
import com.example.decemo.model.Lokal
import com.example.decemo.ui.view.dataForController


class PretragaController() : TypedEpoxyController<dataForController>() {
    override fun buildModels(data: dataForController?) {
        pretragaView {
            id("Pretraga")
        }
        val itemModels = ArrayList<DogadjaliViewModel_>()
        data?.let {
            for (i in 0 until it.dogadjaji.size){
                itemModels.add( DogadjaliViewModel_()
                        .id(it.dogadjaji[i].id)
                        .dogadjaj(it.dogadjaji[i])
                        .context(data.context)
                        .onTouch(View.OnClickListener {o->
                            data.callBack.onDogadjajTouch(it.dogadjaji[i],i)
                        }))
            }
        }
        itemModels.let {
            carousel {
                id("Dogadjaji")
                models(it)
            }
        }


        val vrsteLokalaItems = data?.let {
            it.listaVrteLokala.map {lokal->
                VrstaLokalaViewModel_()
                        .id(lokal.id)
                        .vrstaLokala(lokal)
                        .myListener(View.OnClickListener {
                            data.callBack.click(lokal.id,lokal.vrsta)
                        })
            }
        }
        vrsteLokalaItems?.let {
            carousel {
                id("vrsteLokala")
                models(it)
            }
        }

        textView {
            id("id_lokali")
            text("Lokali")
        }

        data?.let {
            it.lokali.forEach {
                lokaliView {
                    id(it.id)
                    lokal(it)
                    context(data.context)
                    myListener(View.OnClickListener { view->
                        data.callBack.onLokalClick(it)
                    })
                }
            }
        }
    }

   interface changeStatus {
      fun click(id:Int,vrsta:String)
       fun onLokalClick(lokal:Lokal)
       fun onDogadjajTouch(dogadjaj:Dogadjaj,position:Int)

   }



}