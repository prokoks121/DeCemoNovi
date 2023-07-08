package com.example.decemo.coordinator.navigations

import com.example.decemo.coordinator.BaseCoordinator
import com.example.decemo.coordinator.Destination
import com.example.decemo.coordinator.Router
import com.example.decemo.ui.viewmodel.HomeViewModel

class MapCoordinator(router: Router) : BaseCoordinator(router) {

    override fun navigate(data: Any?) {
        router.navigateTo(Destination.MAPS) {
            (it as HomeViewModel).apply {
                goToBar = ::navigateToBar
            }
        }
    }

    private fun navigateToBar(barId: Long) {
        BarCoordinator(router).navigate(barId)
    }
}

