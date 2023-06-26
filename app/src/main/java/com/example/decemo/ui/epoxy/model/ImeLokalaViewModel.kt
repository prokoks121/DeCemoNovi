//package com.example.decemo.ui.epoxy.model
//
//import android.view.View
//import android.widget.TextView
//import com.airbnb.epoxy.EpoxyAttribute
//import com.airbnb.epoxy.EpoxyHolder
//import com.airbnb.epoxy.EpoxyModelClass
//import com.airbnb.epoxy.EpoxyModelWithHolder
//import com.example.decemo.R
//
//@EpoxyModelClass(layout = R.layout.epoxy_ime_lokala_item)
//abstract class ImeLokalaViewModel: EpoxyModelWithHolder<ImeLokalaViewModel.ViewHolder>(){
//    @EpoxyAttribute
//    lateinit var imeLokala: String
//    @EpoxyAttribute
//    lateinit var vrstaLokala: String
//
//    override fun bind(view: ImeLokalaViewModel.ViewHolder) {
//        super.bind(view)
//        view.ime.text = imeLokala
//        view.vrsta.text = vrstaLokala
//
//    }
//    class ViewHolder : EpoxyHolder() {
//        lateinit var ime: TextView
//        lateinit var vrsta: TextView
//
//        override fun bindView(itemView: View) {
//            ime = itemView.findViewById(R.id.imeKafica)
//            vrsta = itemView.findViewById(R.id.vrstaKafica)
//
//        }
//    }
//}