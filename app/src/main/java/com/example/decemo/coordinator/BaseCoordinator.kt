package com.example.decemo.coordinator

abstract class BaseCoordinator(protected val router: Router) {
    abstract fun navigate(data: Any? = null)
}