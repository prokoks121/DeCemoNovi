package com.example.decemo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.decemo.repository.Repository

open class BaseViewModel(val repository: Repository) : ViewModel() {
    private val _errorMessage by lazy { MutableLiveData<String?>() }
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    private val _errorMessageDialog by lazy { MutableLiveData<String?>() }
    val errorMessageDialog: LiveData<String?>
        get() = _errorMessageDialog

    private val _successMessage by lazy { MutableLiveData<String?>() }
    val successMessage: LiveData<String?>
        get() = _successMessage

    private val _onBackPress by lazy { MutableLiveData<Unit?>() }
    val onBackPress: LiveData<Unit?>
        get() = _onBackPress

    open fun onViewCreated() {}

    open fun onViewResumed() {}

    open fun onViewPaused() {}

    open fun onViewStopped() {}

    open fun showErrorBox(errorMessage: String) {
        _errorMessage.value = errorMessage
    }

    open fun showErrorDialog(errorMessage: String) {
        _errorMessageDialog.value = errorMessage
    }

    open fun showSuccessBox(successMessage: String) {
        _successMessage.value = successMessage
    }

    open fun goBack() {
        _onBackPress.value = Unit
    }

    fun clearOnBackPress() {
        _onBackPress.value = null
    }

    fun clearSuccessMessage() {
        _successMessage.value = null
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun clearErrorMessageDialog() {
        _errorMessageDialog.value = null
    }
}