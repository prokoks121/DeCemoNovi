//package com.example.decemo.ui.epoxy.model
//
//import android.content.Context
//import android.util.Log
//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.constraintlayout.widget.ConstraintLayout
//import com.airbnb.epoxy.*
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.example.decemo.R
//import com.example.decemo.model.Lokal
//
//@EpoxyModelClass(layout = R.layout.epoxy_lokali_item)
//abstract class LokaliViewModel : EpoxyModelWithHolder<LokaliViewModel.ViewHolder>() {
//    private val URL = "https://bekmen.rs/api/slike/"
//    @EpoxyAttribute
//    lateinit var context: Context
//    @EpoxyAttribute
//    var myListener: View.OnClickListener? = null
//
//    @EpoxyAttribute
//    lateinit var lokal: Lokal
//    override fun bind(view: ViewHolder) {
//        super.bind(view)
//        view.name.setText(lokal.ime)
//        view.adresa.setText(lokal.adresa)
//        view.vrsta.setText(lokal.vrsta)
//        view.layout.setOnClickListener(myListener)
//        Glide.with(context)
//                .load((URL + lokal.slika))
//                .optionalCircleCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(view.slika)
//    }
//
//    class ViewHolder : EpoxyHolder() {
//        lateinit var name: TextView
//        lateinit var adresa: TextView
//        lateinit var vrsta: TextView
//        lateinit var slika: ImageView
//        lateinit var layout: ConstraintLayout
//        override fun bindView(itemView: View) {
//            name = itemView.findViewById(R.id.imeKafica)
//            adresa = itemView.findViewById(R.id.vrsta)
//            vrsta = itemView.findViewById(R.id.adresa)
//            slika = itemView.findViewById(R.id.slikaLokalaL)
//            layout = itemView.findViewById(R.id.list_item1)
//        }
//    }
//}