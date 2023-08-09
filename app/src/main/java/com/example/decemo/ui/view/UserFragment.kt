package com.example.decemo.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.decemo.R
import com.example.decemo.ui.epoxy.controler.UserReservationController
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.example.decemo.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserFragment : BaseFragment(R.layout.fragment_user) {
    private val userViewModel: UserViewModel by viewModel()
    private lateinit var fullName: TextView
    private lateinit var userId: TextView
    private lateinit var logoutButton: Button
    private lateinit var epoxyRecyclerView: EpoxyRecyclerView
    private lateinit var controller: UserReservationController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fullName = view.findViewById(R.id.userFullName)
        userId = view.findViewById(R.id.userId)
        logoutButton = view.findViewById(R.id.logout)
        epoxyRecyclerView = view.findViewById(R.id.listOfReservations)
        controller = UserReservationController(requireContext())
        epoxyRecyclerView.setController(controller)

        controller.setOnReservationClick {
            userViewModel.onReservationClick(it)
        }

        userViewModel.user.observe(viewLifecycleOwner) {
            fullName.text = it.fullName
        }

        userViewModel.reservations.observe(viewLifecycleOwner) {
            controller.setReservation(it)
        }

        logoutButton.setOnClickListener {
            userViewModel.onLogoutClick()
        }
    }

    override fun getViewModel(): BaseViewModel {
        return userViewModel
    }
}