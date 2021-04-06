package com.example.decemo.model.factory

import com.example.decemo.R
import com.example.decemo.model.VrstaLokala

object VrstaLokalaFactory {
    fun getVrstukafica(): ArrayList<VrstaLokala> {
        val listaVrstaLokala: ArrayList<VrstaLokala> = ArrayList()
        listaVrstaLokala.add(VrstaLokala((R.drawable.ic_coffee_cup), "Kafic", true, 0))
        listaVrstaLokala.add(VrstaLokala((R.drawable.ic_beer_non), "Pub", false, 1))
        listaVrstaLokala.add(VrstaLokala((R.drawable.ic_cocktail_non), "Klub", false, 2))
        listaVrstaLokala.add(VrstaLokala((R.drawable.ic_helm), "Splav", false, 3))
        listaVrstaLokala.add(VrstaLokala((R.drawable.ic_barrel), "Kafana", false, 4))
        return listaVrstaLokala
    }
}