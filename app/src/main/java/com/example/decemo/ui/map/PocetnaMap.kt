@file:Suppress("DEPRECATION")

package com.example.decemo.ui.map

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import com.example.decemo.R
import com.example.decemo.model.Lokal
import com.example.decemo.ui.map.view.BottomView
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.annotations.IconFactory
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.CircleLayer
import com.mapbox.mapboxsdk.style.layers.PropertyFactory


class PocetnaMap(val context: Context, private val mapView: MapView) : PermissionsListener, OnRequestPermissionsResultCallback {
    var listaLokala: ArrayList<Lokal> = arrayListOf()
        set(value) {
            field = value
            markeri()
        }
    private val listaLokalaZaPrikaz: ArrayList<MarkerOptions> = ArrayList()
    private lateinit var mapboxMaps: MapboxMap
    private lateinit var permissionsManager: PermissionsManager
    private var oldZoom = 12.0
    init {
        mapa()
    }
    private fun markeri() {
        listaLokalaZaPrikaz.clear()
        val iconF = IconFactory.getInstance(context)
        for (x in listaLokala) {
            listaLokalaZaPrikaz.add(when (x.vrsta) {
                "Kafic" -> MarkerOptions().position(LatLng(x.lat, x.long)).setTitle(x.ime).icon(iconF.fromResource(R.drawable.coffee))
                "Pab" -> MarkerOptions().position(LatLng(x.lat, x.long)).setTitle(x.ime).icon(iconF.fromResource(R.drawable.beer))
                else -> MarkerOptions().position(LatLng(x.lat, x.long)).setTitle(x.ime).icon(iconF.fromResource(R.drawable.placeholder))
            })
        }
        if (this::mapboxMaps.isInitialized)
            styleMap()
    }
    @SuppressLint("ResourceAsColor")
    private fun mapa() {
        mapView.getMapAsync { mapboxMap: MapboxMap? ->
            mapboxMaps = mapboxMap!!
            mapboxMaps.uiSettings.isAttributionEnabled = false
            mapboxMaps.uiSettings.isLogoEnabled = false
            val position = CameraPosition.Builder()
                    .target(LatLng(44.786604, 20.4717838))
                    .zoom(12.0)
                    .build()
            mapboxMaps.cameraPosition = position
            mapboxMaps.setStyle(
                    Style.MAPBOX_STREETS
            ) { style: Style ->
                enableLocationComponent(style)
                val circleLayer = CircleLayer("layer-id", "source-id")

                circleLayer.setProperties(
                        PropertyFactory.circleRadius(18f),
                        PropertyFactory.circleColor(R.color.black)
                )
                style.addLayer(circleLayer);
                mapboxMaps.addMarkers(listaLokalaZaPrikaz)
                mapboxMaps.isAllowConcurrentMultipleOpenInfoWindows = true
                mapboxMaps.addOnCameraIdleListener {
                    val curentZoom = mapboxMaps.cameraPosition.zoom
                    if (curentZoom > 16 && oldZoom <= 16) {
                        val markers = mapboxMaps.markers
                        for (x in markers) {
                            mapboxMaps.selectMarker(x!!)
                        }
                        promeniUgao(90.0)
                    } else if (curentZoom <= 16 && oldZoom > 16) {
                        mapboxMaps.deselectMarkers()
                        promeniUgao(0.0)
                    }
                    oldZoom = curentZoom
                }
                mapboxMaps.setOnMarkerClickListener { marker: Marker ->
                    val imeLokala = marker.title
                    var id = 0
                    for (i in listaLokala.indices) {
                        if (listaLokala[i].ime == imeLokala) {
                            id = i
                        }
                    }
                    val lokal: Lokal = listaLokala[id]
                    BottomView(lokal, context)
                    true
                }
            }
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

    private fun styleMap() {
        mapboxMaps.getStyle {
            mapboxMaps.clear()
            mapboxMaps.addMarkers(listaLokalaZaPrikaz)
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        Toast.makeText(context, "Dozvoljeno", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            locMap()
        } else {
            Toast.makeText(context, "Nije dozvoljeno", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun promeniUgao(angle: Double) {
        val position = CameraPosition.Builder().tilt(angle).build()
        mapboxMaps.animateCamera(CameraUpdateFactory.newCameraPosition(position), 2000)
    }
}