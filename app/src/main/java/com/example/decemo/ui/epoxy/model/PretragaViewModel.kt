//package com.example.decemo.ui.epoxy.model
//
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.AutoCompleteTextView
//import android.widget.Filter
//import android.widget.TextView
//import androidx.annotation.Nullable
//import com.airbnb.epoxy.EpoxyAttribute
//import com.airbnb.epoxy.EpoxyHolder
//import com.airbnb.epoxy.EpoxyModelClass
//import com.airbnb.epoxy.EpoxyModelWithHolder
//import com.example.decemo.R
//import com.example.decemo.model.Lokal
//
//
//
//
//
//
//
//@EpoxyModelClass(layout = R.layout.epoxy_pretraga_item)
//abstract class PretragaViewModel : EpoxyModelWithHolder<PretragaViewModel.ViewHolder>() {
//
//    @EpoxyAttribute
//    lateinit var onClick:(search: AutoCompleteTextView) ->Unit
//
//    override fun bind(holder: ViewHolder) {
//        super.bind(holder)
//        holder.search.setOnClickListener(View.OnClickListener {
//            onClick(holder.search)
//        })
//
//    }
//
//    inner class ViewHolder : EpoxyHolder() {
//        lateinit var search: AutoCompleteTextView
//        override fun bindView(itemView: View) {
//            search = itemView.findViewById(R.id.search)
//        }
//    }
//
//
//}
//
//
//
