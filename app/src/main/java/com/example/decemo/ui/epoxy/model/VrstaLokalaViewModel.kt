package com.example.decemo.ui.epoxy.model

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.*
import com.example.decemo.R
import com.example.decemo.model.VrstaLokala

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_vrsta_lokala_item)
abstract class VrstaLokalaViewModel : EpoxyModelWithHolder<VrstaLokalaViewModel.ViewHolder>() {
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var vrstaLokala: VrstaLokala


    @EpoxyAttribute
    var myListener: View.OnClickListener? = null
    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.vrsta.setText(vrstaLokala.vrsta)
        holder.img.setImageResource(vrstaLokala.slika)
        holder.layout.setOnClickListener(myListener)
        statusCheck(holder)
    }

    inner class ViewHolder : EpoxyHolder() {
        lateinit var img: ImageView
        lateinit var vrsta: TextView
        lateinit var drawableLokal: GradientDrawable
        lateinit var layout: ConstraintLayout
        override fun bindView(itemView: View) {
            img = itemView.findViewById(R.id.slika_vrste_lokala)
            vrsta = itemView.findViewById(R.id.text_vrste_lokala)
            layout = itemView.findViewById(R.id.vrsta_lokala)
            drawableLokal = layout.getBackground() as GradientDrawable
        }
    }

    private fun statusCheck(holder: ViewHolder) {
        if (vrstaLokala.status) {
            holder.img.setColorFilter(Color.parseColor("#FF5B16"))
            holder.drawableLokal.setStroke(3, Color.parseColor("#FF5B16"))
        } else {
            holder.img.setColorFilter(Color.WHITE)
            holder.drawableLokal.setStroke(3, Color.WHITE);
        }
    }
}