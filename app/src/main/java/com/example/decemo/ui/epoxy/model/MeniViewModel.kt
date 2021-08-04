package com.example.decemo.ui.epoxy.model

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.decemo.R
import io.navendra.nachos.models.epoxy.KotlinHolder

@EpoxyModelClass(layout = R.layout.epoxy_meni_item)
abstract class MeniViewModel: EpoxyModelWithHolder<MeniViewModel.ViewHolder>() {
    @EpoxyAttribute
    lateinit var myListener:View.OnClickListener
    @EpoxyAttribute
     var check: Boolean = true

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        if (check){
            holder.mapa.setTextColor(Color.parseColor("#FF5B16"))
            holder.galerija.setTextColor(Color.parseColor("#FFFFFF"))

        }else{
            holder.galerija.setTextColor(Color.parseColor("#FF5B16"))
            holder.mapa.setTextColor(Color.parseColor("#FF5B16"))

        }
        holder.galerija.setOnClickListener(myListener)
        holder.mapa.setOnClickListener(myListener)

    }
    inner class ViewHolder : KotlinHolder() {
        lateinit var mapa: TextView
        lateinit var galerija: TextView
        override fun bindView(itemView: View) {
            super.bindView(itemView)
            galerija = itemView.findViewById(R.id.galerijaModels)
            mapa = itemView.findViewById(R.id.mapaModels)

        }
    }
}