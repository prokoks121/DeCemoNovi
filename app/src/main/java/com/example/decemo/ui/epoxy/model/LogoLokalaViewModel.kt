//package com.example.decemo.ui.epoxy.model
//
//import android.content.Context
//import android.view.View
//import android.widget.ImageView
//import com.airbnb.epoxy.EpoxyAttribute
//import com.airbnb.epoxy.EpoxyHolder
//import com.airbnb.epoxy.EpoxyModelClass
//import com.airbnb.epoxy.EpoxyModelWithHolder
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.example.decemo.R
//
//@EpoxyModelClass(layout = R.layout.epoxy_logo_lokala_item)
//abstract class LogoLokalaViewModel: EpoxyModelWithHolder<LogoLokalaViewModel.ViewHolder>(){
//
//    private val URL = "https://bekmen.rs/api/slike/"
//
//    @EpoxyAttribute
//    lateinit var context: Context
//
//    @EpoxyAttribute
//    lateinit var img: String
//    override fun bind(view: LogoLokalaViewModel.ViewHolder) {
//        super.bind(view)
//        Glide.with(context)
//            .load(URL + img)
//            .optionalCircleCrop()
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .into(view.slika)
//    }
//
//
//
//
//    class ViewHolder : EpoxyHolder() {
//        lateinit var slika: ImageView
//        override fun bindView(itemView: View) {
//            slika = itemView.findViewById(R.id.slikaLokala)
//        }
//    }
//
//}