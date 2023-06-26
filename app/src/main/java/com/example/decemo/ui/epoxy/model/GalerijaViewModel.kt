//package com.example.decemo.ui.epoxy.model
//
//import android.content.Context
//import android.opengl.Visibility
//import android.util.Log
//import android.view.View
//import android.widget.ImageView
//import androidx.constraintlayout.widget.ConstraintLayout
//import com.airbnb.epoxy.EpoxyAttribute
//import com.airbnb.epoxy.EpoxyHolder
//import com.airbnb.epoxy.EpoxyModelClass
//import com.airbnb.epoxy.EpoxyModelWithHolder
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.example.decemo.R
//
//@EpoxyModelClass(layout = R.layout.epoxy_galerija_item)
//abstract class GalerijaViewModel:EpoxyModelWithHolder<GalerijaViewModel.ViewHolder>() {
//    private val URL = "https://bekmen.rs/api/slike/"
//
//    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
//    lateinit var url:String
//    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
//    lateinit var context: Context
//
//    override fun bind(holder: ViewHolder) {
//        super.bind(holder)
//
//        Glide.with(context)
//                .load(URL + url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.img)
//
//     //   Glide.with(context)
//             //   .load(URL + url)
//            //    .diskCacheStrategy(DiskCacheStrategy.ALL)
//             //   .into(holder.img2)
//    }
//    class ViewHolder:EpoxyHolder(){
//        lateinit var img:ImageView
//        lateinit var img2:ConstraintLayout
//
//        override fun bindView(itemView: View) {
//            img = itemView.findViewById(R.id.galerijaImg)
//            img2 = itemView.findViewById(R.id.galerijaImgcons)
//        }
//
//    }
//
//}