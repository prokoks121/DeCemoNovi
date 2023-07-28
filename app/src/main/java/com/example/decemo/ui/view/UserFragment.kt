package com.example.decemo.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.decemo.R
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.example.decemo.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserFragment : BaseFragment(R.layout.fragment_user) {
    private val userViewModel: UserViewModel by viewModel()
    private lateinit var fullName: TextView
    private lateinit var logoutButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fullName = view.findViewById(R.id.user_name)
        logoutButton = view.findViewById(R.id.logout)

        userViewModel.user.observe(viewLifecycleOwner) {
            fullName.text = it.fullName
        }

        logoutButton.setOnClickListener {
            userViewModel.onLogoutClick()
        }
    }

    override fun getViewModel(): BaseViewModel {
        return userViewModel
    }
}