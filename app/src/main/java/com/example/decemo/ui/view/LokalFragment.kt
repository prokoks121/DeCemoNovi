package com.example.decemo.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.decemo.R
import com.example.decemo.ui.epoxy.controler.LokalController
import com.example.decemo.ui.epoxy.model.GalerijaViewModel_
import com.example.decemo.ui.viewmodel.LokalViewModel


class LokalFragment : Fragment(),LokalController.CallBack {
    private lateinit var controler:LokalController
    private lateinit var viewModel: LokalViewModel
    private val args: LokalFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lokal, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(LokalViewModel::class.java)
        viewModel.lokal.postValue(args.Lokal)
        setEpoxy(view)
        viewModel.lokal.observe(viewLifecycleOwner, Observer {
            controler.lokal = it

        })
        return view
    }

    private fun setEpoxy(view:View){
        val recyclerViewLokali = view.findViewById<EpoxyRecyclerView>(R.id.lokalEpoxy)
        controler = LokalController(this,requireContext())
        recyclerViewLokali.setController(controler)
    }
    override fun hide(view:View) {
        when(view.id){
           R.id.mapaModels ->{
               controler.provera = true
            }
            R.id.galerijaModels ->{
                controler.provera = false
            }
        }
    }


}