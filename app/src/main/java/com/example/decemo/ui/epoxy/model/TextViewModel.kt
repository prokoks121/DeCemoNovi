package com.example.decemo.ui.epoxy.model

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.decemo.R

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_text_item)
abstract class TextViewModel : EpoxyModelWithHolder<TextViewModel.ViewHolder>() {
    @EpoxyAttribute
    lateinit var text: String
    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.text.text = text
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var text: TextView
        override fun bindView(itemView: View) {
            text = itemView.findViewById(R.id.text_epoxy_pretraga)
        }
    }
}