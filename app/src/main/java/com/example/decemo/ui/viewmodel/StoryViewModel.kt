package com.example.decemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.decemo.model.Dogadjaj

class StoryViewModel:ViewModel() {

    val dogadjaj by lazy {
        MutableLiveData<ArrayList<Dogadjaj>>()
    }
    val positon by lazy {
        MutableLiveData<Int>()
    }

    fun setDogadjaj(dogadjaj:ArrayList<Dogadjaj>){
        this.dogadjaj.postValue(dogadjaj)
    }

    fun setPosition(position:Int){
        this.positon.postValue(position)
    }
}