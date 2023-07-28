package com.example.decemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.decemo.repository.Repository
import com.example.decemo.retrofit.dto.UserDto
import kotlinx.coroutines.launch

class UserViewModel(repository: Repository) : BaseViewModel(repository) {
    lateinit var goToLogin: () -> Unit

    private val _user by lazy { MutableLiveData<UserDto>() }
    val user: LiveData<UserDto>
        get() = _user

    private fun getUser() {
        viewModelScope.launch {
            repository.getUser().onSuccess {
                _user.value = it
            }.onFailure {
                goToLogin()
            }
        }
    }

    fun onLogoutClick() {
        repository.logoutUser()
        goToLogin()
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getUser()
    }
}