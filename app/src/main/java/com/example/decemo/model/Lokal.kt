package com.example.decemo.model

data class Lokal(
        val adresa: String,
        val id: Int,
        val ime: String,
        val lat: Double,
        val long: Double,
        val radno: ArrayList<String>,
        val slika: String,
        val telefon: String,
        val usluge: ArrayList<String>,
        val vrsta: String
)