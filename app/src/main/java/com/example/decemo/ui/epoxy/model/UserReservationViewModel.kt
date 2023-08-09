package com.example.decemo.ui.epoxy.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.base.BASE_IMAGES_URL

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_reservation_item)
abstract class UserReservationViewModel : EpoxyModelWithHolder<UserReservationViewModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var onReservationIconClick: () -> Unit

    @EpoxyAttribute
    lateinit var image: String

    @EpoxyAttribute
    lateinit var context: Context

    @EpoxyAttribute
    lateinit var barName: String

    @EpoxyAttribute
    lateinit var numOfPersons: String

    @EpoxyAttribute
    lateinit var reservationDate: String
    override fun bind(holder: ViewHolder) {
        super.bind(holder)

        holder.barName.text = barName
        holder.reservationDate.text = reservationDate
        holder.numOfReservations.text = numOfPersons
        Glide.with(context)
            .load(BASE_IMAGES_URL + image)
            .optionalCircleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.barImage)
        holder.container.setOnClickListener {
            onReservationIconClick()
        }
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var barName: TextView
        lateinit var numOfReservations: TextView
        lateinit var reservationDate: TextView
        lateinit var barImage: ImageView
        lateinit var container: ConstraintLayout
        override fun bindView(itemView: View) {
            barName = itemView.findViewById(R.id.barName)
            numOfReservations = itemView.findViewById(R.id.numOfReservations)
            reservationDate = itemView.findViewById(R.id.reservationDate)
            barImage = itemView.findViewById(R.id.barImage)
            container = itemView.findViewById(R.id.reservationItem)
        }
    }
}