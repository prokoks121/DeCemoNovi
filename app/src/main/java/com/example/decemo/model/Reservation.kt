package com.example.decemo.model

data class Reservation(
    val id: Long,
    val numOfPersons: Int,
    val reservationDay: String,
    val createdAt: String,
    val barId: Long,
    val barName: String,
    val barImage: String
)
