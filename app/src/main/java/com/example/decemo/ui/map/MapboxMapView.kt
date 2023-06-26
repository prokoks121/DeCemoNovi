package com.example.decemo.ui.map

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.example.decemo.R
import com.example.decemo.retrofit.dto.BarDto
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


// Da bude kao komponenta
@SuppressLint("ViewConstructor")
class MapboxMapView @JvmOverloads constructor(
    context: Context, val view: View,
    private val mapView: MapView,
    attrs: AttributeSet? = null,
) : PermissionsListener, OnRequestPermissionsResultCallback, ConstraintLayout(context, attrs) {
    // Ne lista lokala nego da bude objekat neki koji sadrzi koordinate i ikonicu i ime
    private var bars: MutableLiveData<List<BarDto>> = MutableLiveData()

    //    private val listaLokalaZaPrikaz: ArrayList<MarkerOptions> = ArrayList()
    private lateinit var mapboxMaps: MapboxMap
    private lateinit var markerViewManager: MarkerViewManager
    private var markerViews = listOf<MarkerView>()
    private lateinit var permissionsManager: PermissionsManager
    private var oldZoom = 12.0

    init {
        initializeMap()
    }

    private fun generateMarkers(bars: List<BarDto>): List<MarkerView> {
        // TODO(Deprecated da se sredi)
//        val iconF = IconFactory.getInstance(context)
        if (markerViews.isNotEmpty()) {
            markerViews.forEach {
                markerViewManager.removeMarker(it)
            }
        }
        val markers = bars.map { bar ->
            val customView: View = LayoutInflater.from(context).inflate(
                R.layout.custom_marker, null
            )
            customView.layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            customView.setOnClickListener {
//                    BottomView(bar, view, context)
            }

//            when (bar.barType.type) {
//                // TODO(Deprecated da se sredi)
//                // TODO(Da se izbaci deo logike odavde)
//                "Kafići" ->  .setTitle(bar.name).icon(iconF.fromResource(R.drawable.coffee))
//                "Pivnice" ->  .icon(iconF.fromResource(R.drawable.beer))
//                "Kafane" -> .icon(iconF.fromResource(R.drawable.beer))
//                else -> .icon(iconF.fromResource(R.drawable.placeholder))
//            }
            val markerView = MarkerView(LatLng(bar.latitude, bar.longitude), customView)
            markerViewManager.addMarker(MarkerView(LatLng(bar.latitude, bar.longitude), customView))
            markerView
        }
        return markers
//
//        if (this@MapboxMapView::mapboxMaps.isInitialized) {
//            styleMap()
//        }
    }

    @SuppressLint("ResourceAsColor")
    private fun initializeMap() {
        mapView.getMapAsync { mapboxMap: MapboxMap? ->
            mapboxMaps = mapboxMap!!
            mapboxMaps.uiSettings.isAttributionEnabled = false
            mapboxMaps.uiSettings.isLogoEnabled = false
            val position = CameraPosition.Builder()
                // TODO(Kroz konstruktor da se prosledjuje)
                .target(LatLng(44.786604, 20.4717838))
                .zoom(12.0)
                .build()
            mapboxMaps.cameraPosition = position
            mapboxMaps.setStyle(Style.MAPBOX_STREETS) { style: Style ->
                enableLocationComponent(style)
                val circleLayer = CircleLayer("layer-id", "source-id")
                circleLayer.setProperties(
                    PropertyFactory.circleRadius(18f),
                    PropertyFactory.circleColor(R.color.black)
                )
                style.addLayer(circleLayer)
                // TODO(Deprecated da se sredi)
                markerViewManager = MarkerViewManager(mapView, mapboxMaps)
                bars.observe(ViewTreeLifecycleOwner.get(mapView)!!) {
                    markerViews = generateMarkers(it)
                }

//                mapboxMaps.isAllowConcurrentMultipleOpenInfoWindows = true
                onCameraIdle()
            }
        }
    }

    private fun onCameraIdle() {
        mapboxMaps.addOnCameraIdleListener {
            val currentZoom = mapboxMaps.cameraPosition.zoom
            if (currentZoom > 16 && oldZoom <= 16) {
                setAngle(90.0)
            } else if (currentZoom <= 16 && oldZoom > 16) {
                setAngle(0.0)
            }
            oldZoom = currentZoom
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

//    private fun styleMap() {
//        mapboxMaps.getStyle {
//            // TODO(Deprecated da se sredi)
//            mapboxMaps.clear()
//            mapboxMaps.addMarkers(listaLokalaZaPrikaz)
//        }
//    }

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

    fun addMarkers(bars: List<BarDto>) {
        this.bars.value = bars
    }
}