package com.example.decemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.decemo.repository.Repository

class SearchViewModel : ViewModel() {
    val listaLokala by lazy {
        Repository.listaLokala
    }
    val listaDogadjaja by lazy {
        Repository.listaDogadjaja
    }

    fun getListuKafica() {
        Repository.getLokali()
    }

    fun getListuDogadjaja() {
        Repository.getDogadjaje()
    }
}