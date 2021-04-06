package com.example.decemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.decemo.repository.Repository

class HomeViewModel : ViewModel() {
    val listaLokala by lazy {
        Repository.listaLokala
    }

    fun getListuKafica() {
        Repository.getLokali()
    }
}