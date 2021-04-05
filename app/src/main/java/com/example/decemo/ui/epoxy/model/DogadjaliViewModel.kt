package com.example.decemo.ui.epoxy.model

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
import com.example.decemo.model.Dogadjaj
import io.navendra.nachos.models.epoxy.KotlinHolder

@EpoxyModelClass(layout = R.layout.epoxy_dogadjaji_item)

abstract class DogadjaliViewModel: EpoxyModelWithHolder<DogadjaliViewModel.DogadjajiHolder>(){
    @EpoxyAttribute
    lateinit var dogadjaj: Dogadjaj

    private val URL = "https://api.polovnitelefoni.net/slike/"

    @EpoxyAttribute
    lateinit var context:Context

    override fun bind(holder: DogadjajiHolder) {
        super.bind(holder)

        holder.imeLokala.setText(dogadjaj.imeLokala)
        Glide.with(context)
            .load(URL + dogadjaj.slika)
            .optionalCircleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.slikaLokala)

    }

    inner class DogadjajiHolder : KotlinHolder() {

        lateinit var  imeLokala: TextView
        lateinit var slikaLokala: ImageView


        override fun bindView(itemView: View) {
            super.bindView(itemView)
            slikaLokala = itemView.findViewById(R.id.slikaLokala)
            imeLokala = itemView.findViewById(R.id.imeKafica)

        }
    }


}