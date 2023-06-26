//package com.example.decemo.ui.viewmodel
//
//import androidx.lifecycle.ViewModel
//import com.example.decemo.repository.Repository
//
//class SearchViewModel : ViewModel() {
//    val listaLokala by lazy {
//        Repository.locals
//    }
//
//    val listaDogadjaja by lazy {
//        Repository.events
//    }
//
//    fun getListuKafica() {
//        Repository.getAllBars()
//    }
//
//    fun getListuDogadjaja() {
//        Repository.getEvents()
//    }
//}