package com.example.decemo.ui.epoxy.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.model.Event
import com.example.decemo.ui.epoxy.holder.KotlinHolder

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_dogadjaji_item)
abstract class EventEpoxyViewModel : EpoxyModelWithHolder<EventEpoxyViewModel.EventHolder>() {
    @EpoxyAttribute
    lateinit var event: Event

    @EpoxyAttribute
    lateinit var onTouch:View.OnClickListener

    private val URL = "https://bekmen.rs/api/slike/"

    @EpoxyAttribute
    lateinit var context: Context

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(holder: EventHolder) {
        super.bind(holder)
        holder.barName.text = event.name
        Glide.with(context)
                .load(URL + event.name+ "/Story/" + event.imageUrl)
                .optionalCircleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.barImage)

        holder.barImage.setOnClickListener(onTouch)
    }

    inner class EventHolder : KotlinHolder() {
        lateinit var barName: TextView
        lateinit var barImage: ImageView
        override fun bindView(itemView: View) {
            super.bindView(itemView)
            barImage = itemView.findViewById(R.id.slikaLokala)
            barName = itemView.findViewById(R.id.imeKafica)
        }
    }
}