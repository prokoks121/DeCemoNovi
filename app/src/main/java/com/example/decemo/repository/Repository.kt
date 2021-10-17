package com.example.decemo.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.decemo.model.Dogadjaj
import com.example.decemo.model.Lokal
import com.example.decemo.model.factory.VrstaLokalaFactory
import com.example.decemo.retrofit.Connect
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {
    val listaLokala by lazy {
        MutableLiveData<ArrayList<Lokal>>()
    }
    val listaDogadjaja by lazy {
        MutableLiveData<ArrayList<Dogadjaj>>()
    }
    val listaVrstaLokala = VrstaLokalaFactory.getVrstukafica()



    fun getLokali() {
        val call = Connect.retrofitApiInterface.getListuLokala()
        call.enqueue(object : Callback<ArrayList<Lokal>> {
            override fun onFailure(call: Call<ArrayList<Lokal>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                    call: Call<ArrayList<Lokal>>?,
                    response: Response<ArrayList<Lokal>>?
            ) {
                listaLokala.value = response!!.body()
            }
        })
    }

    fun getDogadjaje() {
        val call = Connect.retrofitApiInterface.getListuDogadjaja()
        call.enqueue(object : Callback<ArrayList<Dogadjaj>> {
            override fun onFailure(call: Call<ArrayList<Dogadjaj>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<ArrayList<Dogadjaj>>?, response: Response<ArrayList<Dogadjaj>>?) {
                listaDogadjaja.value = response!!.body()
            }
        })
    }
}