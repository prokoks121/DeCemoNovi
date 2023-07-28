package com.example.decemo.coordinator.navigations

import com.example.decemo.coordinator.BaseCoordinator
import com.example.decemo.coordinator.Destination
import com.example.decemo.coordinator.Router
import com.example.decemo.ui.viewmodel.UserViewModel

class UserCoordinator(router: Router) : BaseCoordinator(router) {
    override fun navigate(data: Any?) {
        router.navigateTo(Destination.USER) {
            (it as UserViewModel).apply {
                goToLogin = ::goToLogin
            }
        }
    }

    private fun goToLogin() {
        LoginCoordinator(router).navigate()
    }
}