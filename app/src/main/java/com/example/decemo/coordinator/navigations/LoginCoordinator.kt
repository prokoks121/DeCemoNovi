package com.example.decemo.coordinator.navigations

import com.example.decemo.coordinator.BaseCoordinator
import com.example.decemo.coordinator.Destination
import com.example.decemo.coordinator.Router
import com.example.decemo.ui.viewmodel.LoginViewModel

class LoginCoordinator(router: Router) : BaseCoordinator(router) {
    override fun navigate(data: Any?) {
        router.navigateTo(Destination.LOGIN) {
            (it as LoginViewModel).apply {
                goToUser = ::navigateToUser
            }
        }
    }

    private fun navigateToUser() {
        //TODO
    }

}