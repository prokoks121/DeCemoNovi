package com.example.decemo.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.decemo.R
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.example.decemo.ui.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var submitButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailInput = view.findViewById(R.id.login_email)
        passwordInput = view.findViewById(R.id.login_password)
        submitButton = view.findViewById(R.id.login_submit)

        submitButton.setOnClickListener {
            loginViewModel.onSubmitClick(emailInput.text.toString(), passwordInput.text.toString())
        }
    }

    override fun getViewModel(): BaseViewModel {
        return loginViewModel
    }
}