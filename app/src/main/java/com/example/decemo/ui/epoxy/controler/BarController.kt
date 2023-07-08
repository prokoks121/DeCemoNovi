package com.example.decemo.ui.epoxy.controler

import android.content.Context
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.example.decemo.R
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.ui.epoxy.model.BarLogoEpoxyModel_
import com.example.decemo.ui.epoxy.model.BarNameEpoxyModel_
import com.example.decemo.ui.epoxy.model.BarServicesEpoxyModel_
import com.example.decemo.ui.epoxy.model.GalleryEpoxyModel_
import com.example.decemo.ui.epoxy.model.MapBoxEpoxyModel_
import com.example.decemo.ui.epoxy.model.MeniEpoxyModel
import com.example.decemo.ui.epoxy.model.MeniEpoxyModel_

class BarController(val context: Context) : EpoxyController() {
    private var mapVisibility = true
    private var bar: BarDto? = null

    fun setBar(bar: BarDto) {
        this.bar = bar
        requestModelBuild()
    }

    //TODO Srediti izgled
    private fun hide(type: MeniEpoxyModel.MeniType) {
        when (type) {
            MeniEpoxyModel.MeniType.MAP -> {
                mapVisible(true)
            }

            MeniEpoxyModel.MeniType.GALLERY -> {
                mapVisible(false)
            }
        }
    }

    private fun mapVisible(visible: Boolean) {
        mapVisibility = visible
        requestModelBuild()
    }

    override fun buildModels() {
        bar?.let { bar ->
            BarLogoEpoxyModel_().apply {
                id("logo")
                img(bar.mainPictureUrl)
                context(this@BarController.context)
                addTo(this@BarController)
            }

            BarNameEpoxyModel_().apply {
                id("barName")
                barName(bar.name)
                barType(bar.barType.type)
                addTo(this@BarController)
            }

            MeniEpoxyModel_().apply {
                id("meni")
                onClick(::hide)
                addTo(this@BarController)
            }

            MapBoxEpoxyModel_().apply {
                id("mapBox")
                context(this@BarController.context)
                lat(bar.latitude)
                lon(bar.longitude)
                barName(bar.name)
                addIf(mapVisibility, this@BarController)
            }

            val gallery = bar.galleryPictureUrls.mapIndexed { i, it ->
                GalleryEpoxyModel_().apply {
                    id("gallery-$i")
                    context(this@BarController.context)
                    url(it)
                }
            }

            CarouselModel_().apply {
                id("carousel-gallery")
                models(gallery)
                hasFixedSize(true)
                addIf(!mapVisibility, this@BarController)
            }

            BarServicesEpoxyModel_().apply {
                id("address")
                barService(bar.address)
                icon(R.drawable.ic_maps_and_flags)
                addTo(this@BarController)
            }

            BarServicesEpoxyModel_().apply {
                id("phone")
                barService(bar.phoneNumber)
                icon(R.drawable.telefon)
                addTo(this@BarController)
            }

            bar.services.forEachIndexed { i, serviceDto ->
                BarServicesEpoxyModel_().apply {
                    id("service-$i")
                    barService(serviceDto.name)
                    icon(R.drawable.ic_check)
                    addTo(this@BarController)
                }
            }
        }
    }
}