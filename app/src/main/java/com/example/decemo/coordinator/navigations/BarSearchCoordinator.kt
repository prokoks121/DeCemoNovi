package com.example.decemo.coordinator.navigations

import com.example.decemo.coordinator.BaseCoordinator
import com.example.decemo.coordinator.Destination
import com.example.decemo.coordinator.Router
import com.example.decemo.ui.viewmodel.BarSearchViewModel

class BarSearchCoordinator(router: Router) : BaseCoordinator(router) {

    override fun navigate(data: Any?) {
        router.navigateTo(Destination.BAR_SEARCH) {
            (it as BarSearchViewModel).apply {
                goToBar = ::navigateToBar
            }
        }
    }

    private fun navigateToBar(barId: Long) {
        BarCoordinator(router).navigate(barId)
    }
}