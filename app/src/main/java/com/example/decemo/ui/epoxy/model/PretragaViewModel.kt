package com.example.decemo.ui.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.decemo.R

@EpoxyModelClass(layout = R.layout.epoxy_pretraga_item)
abstract class PretragaViewModel : EpoxyModelWithHolder<PretragaViewModel.ViewHolder>() {
    inner class ViewHolder : EpoxyHolder() {
        override fun bindView(itemView: View) {
        }
    }
}