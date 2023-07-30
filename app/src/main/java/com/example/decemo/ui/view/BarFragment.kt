package com.example.decemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.decemo.R
import com.example.decemo.ui.epoxy.controler.BarController
import com.example.decemo.ui.viewmodel.BarViewModel
import com.example.decemo.ui.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BarFragment : BaseFragment(R.layout.fragment_lokal) {
    private val barViewModel: BarViewModel by viewModel()
    private lateinit var controller: BarController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        setEpoxy(view)
        barViewModel.bar.observe(viewLifecycleOwner) {
            controller.setBar(it)
        }
        return view
    }

    override fun getViewModel(): BaseViewModel {
        return barViewModel
    }

    private fun setEpoxy(view: View) {
        val epoxyView = view.findViewById<EpoxyRecyclerView>(R.id.lokalEpoxy)
        controller = BarController(requireContext())
        controller.onReservationClick {
            barViewModel.onReservationClick()
        }
        epoxyView.setController(controller)
    }
}