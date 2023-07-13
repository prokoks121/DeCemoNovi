package com.example.decemo.retrofit.dto

import java.time.ZonedDateTime

data class EventDto(
    var id: Long = 0,
    var name: String,
    var imageUrl: String,
//    var createdAt: ZonedDateTime
)