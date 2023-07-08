package com.example.decemo.ui.epoxy.model

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.decemo.R
import com.example.decemo.ui.epoxy.holder.KotlinHolder

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_meni_item)
abstract class MeniEpoxyModel : EpoxyModelWithHolder<MeniEpoxyModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var onClick: (MeniType) -> Unit

    private var selected: MeniType = MeniType.MAP

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.gallery.setOnClickListener {
            if (selected != MeniType.GALLERY) {
                selected = MeniType.GALLERY
                onStatusChange(holder)
                onClick(MeniType.GALLERY)
            }
        }

        holder.map.setOnClickListener {
            if (selected != MeniType.MAP) {
                selected = MeniType.MAP
                onStatusChange(holder)
                onClick(MeniType.MAP)
            }
        }
    }

    private fun onStatusChange(holder: ViewHolder) {
        if (selected == MeniType.MAP) {
            holder.map.setTextColor(Color.parseColor("#FF5B16"))
            holder.gallery.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            holder.gallery.setTextColor(Color.parseColor("#FF5B16"))
            holder.map.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    inner class ViewHolder : KotlinHolder() {
        lateinit var map: TextView
        lateinit var gallery: TextView

        override fun bindView(itemView: View) {
            super.bindView(itemView)
            gallery = itemView.findViewById(R.id.galerijaModels)
            map = itemView.findViewById(R.id.mapaModels)
        }
    }

    enum class MeniType {
        MAP, GALLERY
    }
}