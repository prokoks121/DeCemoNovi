package com.example.decemo.ui.epoxy.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.retrofit.dto.BarDto

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_lokali_item)
abstract class BarEpoxyViewModel : EpoxyModelWithHolder<BarEpoxyViewModel.ViewHolder>() {
    private val URL = "https://bekmen.rs/api/slike/"

    @EpoxyAttribute
    lateinit var context: Context

    @EpoxyAttribute
    var myListener: View.OnClickListener? = null

    @EpoxyAttribute
    lateinit var bar: BarDto

    override fun bind(view: ViewHolder) {
        super.bind(view)
        view.name.text = bar.name
        view.address.text = bar.address
        view.type.text = bar.barType.type
        view.layout.setOnClickListener(myListener)
        Glide.with(context)
            .load((URL + bar.mainPictureUrl))
            .optionalCircleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view.image)
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var name: TextView
        lateinit var address: TextView
        lateinit var type: TextView
        lateinit var image: ImageView
        lateinit var layout: ConstraintLayout

        override fun bindView(itemView: View) {
            name = itemView.findViewById(R.id.imeKafica)
            address = itemView.findViewById(R.id.vrsta)
            type = itemView.findViewById(R.id.adresa)
            image = itemView.findViewById(R.id.slikaLokalaL)
            layout = itemView.findViewById(R.id.list_item1)
        }
    }
}