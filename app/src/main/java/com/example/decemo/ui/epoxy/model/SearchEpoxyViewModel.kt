package com.example.decemo.ui.epoxy.model


import android.annotation.SuppressLint
import android.view.View
import android.widget.AutoCompleteTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.decemo.R

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_pretraga_item)
abstract class SearchEpoxyViewModel : EpoxyModelWithHolder<SearchEpoxyViewModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var onClick: (search: AutoCompleteTextView) -> Unit

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.search.setOnClickListener {
            onClick(holder.search)
        }
    }

    inner class ViewHolder : EpoxyHolder() {
        lateinit var search: AutoCompleteTextView

        override fun bindView(itemView: View) {
            search = itemView.findViewById(R.id.search)
        }
    }
}



