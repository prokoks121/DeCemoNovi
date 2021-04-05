package com.example.decemo.retrofit


import com.example.decemo.model.Dogadjaj
import com.example.decemo.model.Lokal
import retrofit2.Call
import retrofit2.http.GET

interface ApiConntect {
    @GET("api.php")
    fun getListuLokala(): Call<ArrayList<Lokal>>
    @GET("api-dogadjaji.php")
    fun getListuDogadjaja(): Call<ArrayList<Dogadjaj>>
}