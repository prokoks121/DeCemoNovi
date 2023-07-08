package com.example.decemo.coordinator

import com.example.decemo.ui.viewmodel.BaseViewModel

abstract class Router {
    abstract fun navigateTo(destination: Destination, onFinish: (baseViewModel: BaseViewModel) -> Unit)
}