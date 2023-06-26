package com.example.decemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.decemo.repository.Repository

open class BaseViewModel(repository: Repository) : ViewModel() {

    open fun onViewCreated() {}

    open fun onViewResumed() {}

    open fun onViewPaused() {}

    open fun onViewStopped() {}
}