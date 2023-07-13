package com.example.decemo.retrofit.dto


data class BarDto(
    var id: Long,
    var name: String,
    var address: String,
    var services: MutableList<ServiceDto> = mutableListOf(),
    var workTime: MutableList<String> = mutableListOf(),
    var latitude: Double,
    var longitude: Double,
    var phoneNumber: String,
    var mainPictureUrl: String,
    var galleryPictureUrls: MutableList<String> = mutableListOf(),
    var barType: BarTypeDto
)