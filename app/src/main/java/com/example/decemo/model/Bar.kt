package com.example.decemo.model


data class Bar(
    var id: Long,
    var name: String,
    var address: String,
    var services: MutableList<BarService> = mutableListOf(),
    var workTime: MutableList<String> = mutableListOf(),
    var latitude: Double,
    var longitude: Double,
    var phoneNumber: String,
    var mainPictureUrl: String,
    var galleryPictureUrls: MutableList<String> = mutableListOf(),
    var barType: BarType
)