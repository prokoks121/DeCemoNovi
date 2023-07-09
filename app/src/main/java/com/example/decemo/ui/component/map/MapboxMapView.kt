package com.example.decemo.ui.component.map

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import com.example.decemo.R
import com.example.decemo.ui.component.map.model.Marker
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager
import com.mapbox.mapboxsdk.style.layers.CircleLayer
import com.mapbox.mapboxsdk.style.layers.PropertyFactory

@SuppressLint("ViewConstructor")
class MapboxMapView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : PermissionsListener, OnRequestPermissionsResultCallback,
    MapView(context, attrs) {
    private var markers: List<Marker> = listOf()
    private lateinit var mapboxMaps: MapboxMap
    private lateinit var markerViewManager: MarkerViewManager
    private var markerViews = listOf<MarkerView>()
    private lateinit var permissionsManager: PermissionsManager
    private var lastZoom = 12.0
    private var position: CameraPosition? = null
    var onMarkerClick: (marker: Marker) -> Unit = {}

    init {
        initializeMap()
    }

    @SuppressLint("InflateParams")
    private fun generateMarkers(markers: List<Marker>): List<MarkerView> {
        if (!this::markerViewManager.isInitialized) {
            return listOf()
        }

        if (markerViews.isNotEmpty()) {
            markerViews.forEach {
                markerViewManager.removeMarker(it)
            }
        }
        val viewMarkers = markers.map { marker ->
            val customView: View = LayoutInflater.from(context).inflate(R.layout.custom_marker, null)
            val markerIcon = customView.findViewById<ImageView>(R.id.mapViewIcon)

            customView.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            customView.setOnClickListener {
                onMarkerClick(marker)
            }

            when (marker.type) {
                // TODO(Deprecated da se sredi)
                // TODO(Da se izbaci deo logike odavde)
                "Kafići" -> markerIcon.setImageResource(R.drawable.coffee)
                "Pivnice" -> markerIcon.setImageResource(R.drawable.beer)
                "Kafane" -> markerIcon.setImageResource(R.drawable.beer)
                else -> markerIcon.setImageResource(R.drawable.placeholder)
            }
            val markerView = MarkerView(LatLng(marker.lat, marker.lon), customView)
            markerViewManager.addMarker(markerView)
            markerView
        }
        return viewMarkers
    }

    @SuppressLint("ResourceAsColor")
    private fun initializeMap() {
        this.getMapAsync { mapboxMap: MapboxMap? ->
            mapboxMaps = mapboxMap!!
            mapboxMaps.uiSettings.isAttributionEnabled = false
            mapboxMaps.uiSettings.isLogoEnabled = false

            if (position != null) {
                mapboxMaps.cameraPosition = position!!
            }
            mapboxMaps.setStyle(Style.MAPBOX_STREETS) { style: Style ->
                enableLocationComponent(style)
                val circleLayer = CircleLayer("layer-id", "source-id")
                circleLayer.setProperties(
                    PropertyFactory.circleRadius(18f),
                    PropertyFactory.circleColor(R.color.black)
                )
                style.addLayer(circleLayer)
                markerViewManager = MarkerViewManager(this, mapboxMaps)
                markerViews = generateMarkers(markers)
                onCameraIdle()
            }
        }
    }

    private fun onCameraIdle() {
        mapboxMaps.addOnCameraIdleListener {
            val currentZoom = mapboxMaps.cameraPosition.zoom
            if (currentZoom > 16 && lastZoom <= 16) {
                setAngle(90.0)
            } else if (currentZoom <= 16 && lastZoom > 16) {
                setAngle(0.0)
            }
            lastZoom = currentZoom
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(loadedMapStyle: Style) {
        if (PermissionsManager.areLocationPermissionsGranted(context)) {
            val locationComponent = mapboxMaps.locationComponent
            locationComponent.activateLocationComponent(
                LocationComponentActivationOptions.builder(context, loadedMapStyle)
                    .build()
            )
            locationComponent.isLocationComponentEnabled = true
            locationComponent.cameraMode = CameraMode.TRACKING
            locationComponent.renderMode = RenderMode.COMPASS
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(context as Activity)
        }
    }

    private fun locMap() {
        mapboxMaps.getStyle { loadedMapStyle: Style ->
            enableLocationComponent(
                loadedMapStyle
            )
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        // TODO(U string)
        Toast.makeText(context, "Dozvoljeno", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            locMap()
        } else {
            // TODO(U string)
            Toast.makeText(context, "Nije dozvoljeno", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun setAngle(angle: Double) {
        val position = CameraPosition.Builder().tilt(angle).build()
        // TODO(U konstantu)
        mapboxMaps.animateCamera(CameraUpdateFactory.newCameraPosition(position), 2000)
    }

    fun addMarkers(markers: List<Marker>) {
        this.markers = markers
        markerViews = generateMarkers(markers)
    }

    fun setCameraPosition(lat: Double, lon: Double, zoom: Double) {
        position = CameraPosition.Builder()
            .target(LatLng(lat, lon))
            .zoom(zoom)
            .build()

        if (this::markerViewManager.isInitialized) {
            mapboxMaps.cameraPosition = position!!
        }
    }
}