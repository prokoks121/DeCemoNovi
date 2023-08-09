package com.example.decemo.ui.epoxy.controler

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.example.decemo.model.Reservation
import com.example.decemo.ui.epoxy.model.userReservationView

class UserReservationController(private val context: Context) : EpoxyController() {
    private var reservations: List<Reservation> = listOf()
    private var onReservationClick: (reservationId: Long) -> Unit = {}

    fun setReservation(reservations: List<Reservation>) {
        if (reservations.isEmpty() && this.reservations.isEmpty()) {
            return
        }
        this.reservations = reservations.toMutableList()
        requestModelBuild()
    }

    fun setOnReservationClick(onReservationClick: (reservationId: Long) -> Unit) {
        this.onReservationClick = onReservationClick
    }

    override fun buildModels() {
        reservations.forEachIndexed { index, reservation ->
            userReservationView {
                id("reservation-$index")
                context(context)
                image(reservation.barImage)
                barName(reservation.barName)
                numOfPersons(reservation.numOfPersons.toString())
                reservationDate(reservation.reservationDay.substringBeforeLast("/"))
                onReservationIconClick {
                    onReservationClick(reservation.id)
                }
            }
        }
    }
}