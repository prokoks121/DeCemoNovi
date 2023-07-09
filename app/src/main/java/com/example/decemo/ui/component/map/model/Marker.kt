package com.example.decemo.ui.component.map.model

data class Marker(
    val id: Long,
    val lat: Double,
    val lon: Double,
    val title: String,
    val type: String
)