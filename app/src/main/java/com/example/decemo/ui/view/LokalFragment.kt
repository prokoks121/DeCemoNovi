package com.example.decemo.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.decemo.R
import com.example.decemo.ui.epoxy.controler.LokalController
import com.example.decemo.ui.viewmodel.LokalViewModel


class LokalFragment : Fragment() {
    private lateinit var controler:LokalController
    private lateinit var viewModel: LokalViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lokal, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(LokalViewModel::class.java)
        setEpoxy(view)

        viewModel.lokal.observe(viewLifecycleOwner, Observer {
            controler.setData(it,context)
        })
        return view
    }

    private fun setEpoxy(view:View){
        val recyclerViewLokali = view.findViewById<EpoxyRecyclerView>(R.id.lokalEpoxy)
        controler = LokalController()
        recyclerViewLokali.setController(controler)

    }

}