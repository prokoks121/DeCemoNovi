package com.example.decemo.ui.epoxy.model

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.decemo.R

@EpoxyModelClass(layout = R.layout.epoxy_lokal_usluge)
abstract class LokalUslugeViewModel: EpoxyModelWithHolder<LokalUslugeViewModel.ViewHolder>() {
    @EpoxyAttribute
    lateinit var type: String
    @EpoxyAttribute
     var icon: Int = 0
    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.textVrstaUsluge.text = type
        if (icon != 0)
            holder.iconVrstaUsluge.setImageResource(icon)
    }

    class ViewHolder(): EpoxyHolder(){
        lateinit var textVrstaUsluge: TextView
        lateinit var iconVrstaUsluge: ImageView
        override fun bindView(itemView: View) {
            textVrstaUsluge = itemView.findViewById(R.id.textVrstaUsluge)
            iconVrstaUsluge = itemView.findViewById(R.id.iconVrstaUsluge)
        }

    }
}