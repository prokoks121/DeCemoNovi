package com.example.decemo.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.decemo.R
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.example.decemo.ui.viewmodel.RegistrationViewModel
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {
    private val registrationViewModel: RegistrationViewModel by viewModel()
    private lateinit var submitButton: Button
    private lateinit var fullNameTextView: TextInputEditText
    private lateinit var emailTextView: TextInputEditText
    private lateinit var passwordTextView: TextInputEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton = view.findViewById(R.id.registration_submit)
        fullNameTextView = view.findViewById(R.id.reg_full_name)
        emailTextView = view.findViewById(R.id.reg_email)
        passwordTextView = view.findViewById(R.id.reg_password)

        submitButton.setOnClickListener {
            val fullName = fullNameTextView.text.toString()
            val email = emailTextView.text.toString()
            val password = passwordTextView.text.toString()

            registrationViewModel.onRegistrationSubmit(fullName, email, password)
        }
    }

    override fun getViewModel(): BaseViewModel {
        return registrationViewModel
    }
}