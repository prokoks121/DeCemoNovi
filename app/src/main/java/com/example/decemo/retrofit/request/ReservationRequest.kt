package com.example.decemo.retrofit.request

data class ReservationRequest(
    var numOfPersons: Int,
    var reservationDay: String,
    var barId: Long
)