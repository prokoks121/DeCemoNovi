package com.example.decemo.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.decemo.R
import com.example.decemo.ui.map.PocetnaMap
import com.example.decemo.ui.viewmodel.HomeViewModel
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView

class HomeFragment : Fragment() {
    lateinit var viewModel: HomeViewModel
    var mapView: MapView? = null
    lateinit var map: PocetnaMap
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(requireActivity(), getString(R.string.mapbox_access_token));
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        mapView = view.findViewById(R.id.mapView)
        mapView!!.onCreate(savedInstanceState)
        map = PocetnaMap(requireContext(), mapView!!)
        viewModel.listaLokala.observe(viewLifecycleOwner, Observer {
            map.listaLokala = it
        })
    }
}