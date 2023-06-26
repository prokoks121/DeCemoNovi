package com.example.decemo.retrofit.dto


data class BarDto(
    var id: Long,
    var name: String,
    var address: String,
    var services: MutableList<ServiceDto> = mutableListOf(),
    var workTime: MutableList<String>,
    var latitude: Double,
    var longitude: Double,
    var phoneNumber: String,
    var mainPictureUrl: String,
    var galleryPictureUrls: MutableList<String>,
    var barType: BarTypeDto,
    var events: MutableList<EventDto> = mutableListOf()
)