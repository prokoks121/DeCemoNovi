package com.example.decemo.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.decemo.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel(repository: Repository) : BaseViewModel(repository) {

    lateinit var goToUser: () -> Unit

    fun onSubmitClick(email: String, password: String) {
        viewModelScope.launch {
            repository.loginUser(email, password).onSuccess {
                println("User is login")
//                goToUser()
            }.onFailure {
                println("User is invalid")
                // TODO() show popup
            }
        }
    }
}