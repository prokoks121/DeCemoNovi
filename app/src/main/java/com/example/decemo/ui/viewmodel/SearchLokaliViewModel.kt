package com.example.decemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.decemo.model.Lokal
import com.example.decemo.repository.Repository

class SearchLokaliViewModel:ViewModel() {

    val listaLokala by lazy {
        Repository.listaLokala
    }
    val filterListLokala by lazy {
        MutableLiveData<ArrayList<Lokal>>()
    }

    fun setFilteredList(list:ArrayList<Lokal>){
        filterListLokala.value = list
    }
}