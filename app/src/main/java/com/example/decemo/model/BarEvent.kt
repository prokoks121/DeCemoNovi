package com.example.decemo.model

data class BarEvent(
    var barId: Long,
    var barName: String,
    var barImageUrl: String,
    var events: List<Event>
)