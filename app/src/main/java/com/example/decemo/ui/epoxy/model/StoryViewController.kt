package com.example.decemo.ui.epoxy.model

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.*
import com.example.decemo.R
import com.example.decemo.model.Dogadjaj
import com.example.decemo.ui.view.StoryFragment

@EpoxyModelClass(layout = R.layout.carousel_epoxy)
abstract class StoryViewController:EpoxyModelWithHolder<StoryViewController.ViewHolder>() {

@EpoxyAttribute
lateinit var data:StoryFragment.Data

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.recyclerView.layoutManager = LinearLayoutManager(data.activity,LinearLayoutManager.VERTICAL,false)

    }


    class ViewHolder:EpoxyHolder(){
        lateinit var recyclerView: EpoxyRecyclerView
        override fun bindView(itemView: View) {
            recyclerView = itemView.findViewById(R.id.storyEpoxyRecyclerView)
        }
    }

    class StoryEpoxyController:TypedEpoxyController<StoryFragment.Data>(){
        override fun buildModels(data: StoryFragment.Data?) {
            var i =0
            data?.let {
                it.dogadjaj.forEach {dog->
                    storyView {
                        id(i++)
                        context(data.activity)
                        dogadjaj(dog)
                    }
                }
            }
        }

    }
}