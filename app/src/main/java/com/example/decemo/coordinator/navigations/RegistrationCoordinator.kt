package com.example.decemo.coordinator.navigations

import com.example.decemo.coordinator.BaseCoordinator
import com.example.decemo.coordinator.Destination
import com.example.decemo.coordinator.Router
import com.example.decemo.ui.viewmodel.RegistrationViewModel

class RegistrationCoordinator(router: Router) : BaseCoordinator(router) {
    override fun navigate(data: Any?) {
        router.navigateTo(Destination.REGISTRATION) {
            (it as RegistrationViewModel).apply {
                goToUser = ::navigateToUser
            }
        }
    }

    private fun navigateToUser() {
        UserCoordinator(router).navigate()
    }
}