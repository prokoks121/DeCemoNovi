package com.example.decemo.ui.epoxy.model

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.decemo.R
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style


@EpoxyModelClass(layout = R.layout.epoxy_map_box_item)
abstract class MapBoxViewModel: EpoxyModelWithHolder<MapBoxViewModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var context: Context
    @EpoxyAttribute
     var lat: Double?= null
    @EpoxyAttribute
     var lon: Double?= null
    @EpoxyAttribute
    lateinit var ime: String
    override fun bind(view: MapBoxViewModel.ViewHolder) {
        super.bind(view)
view.mapView.getMapAsync { mapboxMap: MapboxMap ->
    mapboxMap.uiSettings.isAttributionEnabled = false
    mapboxMap.uiSettings.isLogoEnabled = false
    val position = CameraPosition.Builder()
        .target(LatLng(lat!!, lon!!))
        .zoom(13.5)
        .build()
    mapboxMap.cameraPosition = position
    mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->

        // Map is set up and the style has loaded. Now you can add data or make other map adjustments
        mapboxMap.addMarker(
            MarkerOptions()
                .position(LatLng(lat!!, lon!!))
                .title(ime)
        )
    }
}

    }

    class ViewHolder : EpoxyHolder() {
        lateinit var mapView: MapView
        override fun bindView(itemView: View) {
            mapView = itemView.findViewById(R.id.mapView)




        }
    }
}