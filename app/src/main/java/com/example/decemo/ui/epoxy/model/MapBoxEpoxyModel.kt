package com.example.decemo.ui.epoxy.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.decemo.R
import com.example.decemo.ui.component.map.MapboxMapView
import com.example.decemo.ui.component.map.model.Marker


@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_map_box_item)
abstract class MapBoxEpoxyModel : EpoxyModelWithHolder<MapBoxEpoxyModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var context: Context

    @EpoxyAttribute
    var lat: Double? = null

    @EpoxyAttribute
    var lon: Double? = null

    @EpoxyAttribute
    lateinit var barType: String

    @EpoxyAttribute
    lateinit var barName: String

    override fun bind(view: ViewHolder) {
        super.bind(view)
        view.mapView.setCameraPosition(lat!!, lon!!, 13.5)
        view.mapView.addMarkers(listOf(Marker(0, lat!!, lon!!, barName, barType)))
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var mapView: MapboxMapView

        override fun bindView(itemView: View) {
            mapView = itemView.findViewById(R.id.mapView)
        }
    }
}