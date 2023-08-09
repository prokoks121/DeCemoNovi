package com.example.decemo.ui.epoxy.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.base.BASE_IMAGES_URL

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_logo_lokala_item)
abstract class BarLogoEpoxyModel : EpoxyModelWithHolder<BarLogoEpoxyModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var context: Context

    @EpoxyAttribute
    lateinit var onReservationIconClick: () -> Unit

    @EpoxyAttribute
    lateinit var img: String
    override fun bind(view: ViewHolder) {
        super.bind(view)
        Glide.with(context)
            .load(BASE_IMAGES_URL + img)
            .optionalCircleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view.img)
        view.reservationIcon.setOnClickListener {
            onReservationIconClick()
        }
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var img: ImageView
        lateinit var reservationIcon: ImageView

        override fun bindView(itemView: View) {
            img = itemView.findViewById(R.id.slikaLokala)
            reservationIcon = itemView.findViewById(R.id.reservation_icon)
        }
    }
}