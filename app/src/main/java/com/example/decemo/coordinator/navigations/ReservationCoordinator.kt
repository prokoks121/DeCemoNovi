package com.example.decemo.coordinator.navigations

import com.example.decemo.coordinator.BaseCoordinator
import com.example.decemo.coordinator.Destination
import com.example.decemo.coordinator.Router
import com.example.decemo.ui.viewmodel.ReservationViewModel

class ReservationCoordinator(router: Router) : BaseCoordinator(router) {
    override fun navigate(data: Any?) {
        router.navigateTo(Destination.RESERVATION) {
            (it as ReservationViewModel).apply {
                barId = data as Long?
            }
        }
    }
}