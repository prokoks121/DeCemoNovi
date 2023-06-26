//package com.example.decemo.ui.epoxy.model
//
//import android.view.View
//import androidx.viewpager2.widget.ViewPager2
//import com.airbnb.epoxy.EpoxyController
//import com.airbnb.epoxy.EpoxyHolder
//import com.airbnb.epoxy.EpoxyModel
//import com.airbnb.epoxy.EpoxyModelClass
//import com.example.decemo.R
//
//@EpoxyModelClass(layout = R.layout.epoxy_loka_view_pager)
//abstract class LokalViewPager2:EpoxyModel<LokalViewPager2.ViewHolder>() {
//
//    override fun bind(view: ViewHolder) {
//        super.bind(view)
//
//      //  view.viewPager2.adapter = Controller()
//
//    }
//
//    class ViewHolder:EpoxyHolder(){
//        lateinit var viewPager2: ViewPager2
//
//        override fun bindView(itemView: View) {
//
//            viewPager2 = itemView.findViewById(R.id.viewPager2Lokal)
//
//            }
//
//    }
//
//    class Controller:EpoxyController(){
//
//        override fun buildModels() {
//            return
//        }
//
//
//    }
//}