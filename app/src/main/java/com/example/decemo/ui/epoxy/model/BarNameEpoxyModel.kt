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
@EpoxyModelClass(layout = R.layout.epoxy_ime_lokala_item)
abstract class BarNameEpoxyModel : EpoxyModelWithHolder<BarNameEpoxyModel.ViewHolder>() {
    @EpoxyAttribute
    lateinit var barName: String

    @EpoxyAttribute
    lateinit var barType: String

    override fun bind(view: ViewHolder) {
        super.bind(view)
        view.barName.text = barName
        view.barType.text = barType
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var barName: TextView
        lateinit var barType: TextView

        override fun bindView(itemView: View) {
            barName = itemView.findViewById(R.id.imeKafica)
            barType = itemView.findViewById(R.id.vrstaKafica)
        }
    }
}