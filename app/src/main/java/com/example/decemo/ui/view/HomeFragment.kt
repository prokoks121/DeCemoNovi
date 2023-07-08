package com.example.decemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.decemo.R
import com.example.decemo.ui.component.FilterBottomView
import com.example.decemo.ui.component.map.MapboxMapView
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.example.decemo.ui.viewmodel.HomeViewModel
import com.mapbox.mapboxsdk.Mapbox
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var mapView: MapboxMapView
    private lateinit var filterIcon: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Mapbox.getInstance(requireActivity(), getString(R.string.mapbox_access_token))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.mapView)
        filterIcon = view.findViewById(R.id.mainFilterMapIcon)
        mapView.onCreate(savedInstanceState)

        mapView.setOnBottomViewClick {
            homeViewModel.onBarClick(it.id)
        }

        homeViewModel.bars.observe(viewLifecycleOwner) {
            mapView.addMarkers(it)
        }

        filterIcon.setOnClickListener {
            FilterBottomView(view, requireContext(), homeViewModel.barTypes).setOnSwitchListener { filter, newStatus ->
                homeViewModel.updateMapFilterStatus(filter, newStatus)
            }
        }
    }

    override fun getViewModel(): BaseViewModel {
        return homeViewModel
    }
}