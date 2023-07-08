package com.example.decemo.coordinator.navigations

import com.example.decemo.coordinator.BaseCoordinator
import com.example.decemo.coordinator.Destination
import com.example.decemo.coordinator.Router
import com.example.decemo.ui.viewmodel.BarViewModel

class BarCoordinator(router: Router) : BaseCoordinator(router) {

    override fun navigate(data: Any?) {
        val barId = data as Long
        router.navigateTo(Destination.BAR) {
            (it as BarViewModel).apply {
                this.barId = barId
            }
        }
    }
}