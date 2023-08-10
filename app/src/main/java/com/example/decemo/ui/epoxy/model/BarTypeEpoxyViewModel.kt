package com.example.decemo.ui.epoxy.model

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.base.ICONS_URL
import com.example.decemo.model.BarType

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_vrsta_lokala_item)
abstract class BarTypeEpoxyViewModel : EpoxyModelWithHolder<BarTypeEpoxyViewModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var barType: BarType

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var status: Boolean? = null

    @EpoxyAttribute
    var myListener: View.OnClickListener? = null

    @EpoxyAttribute
    lateinit var context: Context

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.type.text = barType.type

        Glide.with(context)
            .load(ICONS_URL + barType.iconUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.img)

        holder.layout.setOnClickListener(myListener)
        statusCheck(holder)
    }

    inner class ViewHolder : EpoxyHolder() {
        lateinit var img: ImageView
        lateinit var type: TextView
        lateinit var drawable: GradientDrawable
        lateinit var layout: ConstraintLayout

        override fun bindView(itemView: View) {
            img = itemView.findViewById(R.id.slika_vrste_lokala)
            type = itemView.findViewById(R.id.text_vrste_lokala)
            layout = itemView.findViewById(R.id.vrsta_lokala)
            drawable = layout.background as GradientDrawable
        }
    }

    private fun statusCheck(holder: ViewHolder) {
        if (status == true) {
            holder.img.setColorFilter(Color.parseColor("#FF5B16"))
            holder.drawable.setStroke(3, Color.parseColor("#FF5B16"))
        } else {
            holder.img.setColorFilter(Color.WHITE)
            holder.drawable.setStroke(3, Color.WHITE)
        }
    }
}