package com.example.decemo.retrofit.dto

data class BarEvent(
    var barId: Long,
    var barName: String,
    var barImageUrl: String,
    var events: List<EventDto>
)