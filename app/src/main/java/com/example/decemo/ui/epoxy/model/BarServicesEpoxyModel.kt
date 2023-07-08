package com.example.decemo.ui.epoxy.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.decemo.R

@EpoxyModelClass(layout = R.layout.epoxy_lokal_usluge)
abstract class BarServicesEpoxyModel : EpoxyModelWithHolder<BarServicesEpoxyModel.ViewHolder>() {
    @EpoxyAttribute
    lateinit var barService: String

    @EpoxyAttribute
    var icon: Int = 0

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.serviceName.text = barService
        if (icon != 0) {
            holder.serviceIcon.setImageResource(icon)
        }
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var serviceName: TextView
        lateinit var serviceIcon: ImageView

        override fun bindView(itemView: View) {
            serviceName = itemView.findViewById(R.id.textVrstaUsluge)
            serviceIcon = itemView.findViewById(R.id.iconVrstaUsluge)
        }
    }
}