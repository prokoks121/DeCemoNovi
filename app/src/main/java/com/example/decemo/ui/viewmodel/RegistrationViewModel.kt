package com.example.decemo.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.decemo.repository.Repository
import kotlinx.coroutines.launch

class RegistrationViewModel(repository: Repository) : BaseViewModel(repository) {
    lateinit var goToUser: () -> Unit

    fun onRegistrationSubmit(fullName: String, email: String, password: String) {
        viewModelScope.launch {
            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                showErrorBox("Sva polja su obavezna.")
                return@launch
            }
            repository.registrationUser(fullName, email, password).onSuccess {
                goToUser()
            }.onFailure {
                showErrorBox(it.message ?: "Doslo je do greske prilikom kreiranja korisnika")
            }
        }
    }
}