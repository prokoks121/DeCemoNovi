package com.example.decemo.ui.epoxy.model

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.Display
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.decemo.R
import com.example.decemo.model.Dogadjaj
import com.example.decemo.ui.view.StoryFragment


private val URL = "https://api.polovnitelefoni.net/slike/"
@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.epoxy_story)
abstract class StoryViewModel:EpoxyModelWithHolder<StoryViewModel.ViewHolder>() {

    @EpoxyAttribute
        lateinit var dogadjaj: ArrayList<Dogadjaj>
    @EpoxyAttribute
        lateinit var context: Activity
   // @EpoxyAttribute
    //    lateinit var onTouchListener: View.OnTouchListener

         var position:Int = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        val display: Display = context.getWindowManager().getDefaultDisplay()
        val size = Point()
        display.getSize(size)
        val width = size.x

        setView(holder)

        holder.layout.setOnTouchListener { v, event ->
            val x = event!!.x.toInt()

            when (event.action) {
                MotionEvent.ACTION_UP -> storyMove(width.toDouble(), x.toDouble(),holder)

            }

            true
        }
    }
    private fun storyMove(width:Double,x:Double,holder: ViewHolder){
        if (width/x < 2)
            if (++position > dogadjaj.size)
                setView(holder)
            else
               Log.d("Provera","123")

        else
            if (--position > -1)
                setView(holder)
            else
                Log.d("Provera","123")

    }

    private fun setView(holder: ViewHolder){

        holder.storyImage.layout(0, 0, 0, 0)

        Glide.with(context)
                .load(URL + dogadjaj[position].slika)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.storyImage)


        Glide.with(context)
                .load(URL + dogadjaj[position].slika)
                .transform( MultiTransformation(CenterCrop(), StoryFragment.BlurTransformation(context)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.storyImageBackground)


        Glide.with(context)
                .load(URL + dogadjaj[position].slika)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(holder.storyLokalImage)

        holder.storyLokalName.setText(dogadjaj[position].imeLokala)
    }


    class ViewHolder:EpoxyHolder(){
        lateinit var storyImage: ImageView
        lateinit var storyImageBackground: ImageView
        lateinit var storyLokalImage: ImageView
        lateinit var storyLokalName: TextView
        lateinit var layout: ConstraintLayout

        override fun bindView(view: View) {
            storyImage = view.findViewById(R.id.StoryImage)
            storyImageBackground = view.findViewById(R.id.StoryImageBackground)
            storyLokalImage = view.findViewById(R.id.StoryLocalImage)
            storyLokalName= view.findViewById(R.id.StoryLokalName)
            layout = view.findViewById(R.id.StoryFragment)
        }

    }
}