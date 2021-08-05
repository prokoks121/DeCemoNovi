package com.example.decemo.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.model.Dogadjaj

class StoryViewFragment(val dogadjaj:ArrayList<Dogadjaj>,val callBacks: callBack) : Fragment() {

    lateinit var storyImage: ImageView
    lateinit var storyImageBackground: ImageView
    lateinit var storyLokalImage: ImageView
    lateinit var storyLokalName: TextView
    lateinit var layout: ConstraintLayout
    var position = 0
    private val URL = "https://api.polovnitelefoni.net/slike/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.epoxy_story, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        position = 0
        storyImage = view.findViewById(R.id.StoryImage)
        storyImageBackground = view.findViewById(R.id.StoryImageBackground)
        storyLokalImage = view.findViewById(R.id.StoryLocalImage)
        storyLokalName= view.findViewById(R.id.StoryLokalName)
        layout = view.findViewById(R.id.StoryFragment)
        val display: Display =requireActivity().getWindowManager().getDefaultDisplay()
        val size = Point()
        display.getSize(size)
        val width = size.x


        setView()

        layout.setOnTouchListener { v, event ->
            val x = event!!.x.toInt()

            when (event.action) {
                MotionEvent.ACTION_UP -> storyMove(width.toDouble(), x.toDouble())

            }

            true
        }

    }


    private fun setView(){

        storyImage.layout(0, 0, 0, 0)

        Glide.with(requireActivity())
            .load(URL + dogadjaj[position].slika)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(storyImage)


        Glide.with(requireActivity())
            .load(URL + dogadjaj[position].slika)
            //   .transform( MultiTransformation(CenterCrop(), StoryFragment.BlurTransformation(context)))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(storyImageBackground)


        Glide.with(requireActivity())
            .load(URL + dogadjaj[position].slika)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .circleCrop()
            .into(storyLokalImage)

       storyLokalName.setText(dogadjaj[position].imeLokala)
    }

    private fun storyMove(width:Double,x:Double){
        if (width/x < 2) {
            val pom = position +1
            if (pom < dogadjaj.size) {
                position++
                setView()
            }
            else
              callBacks.next()
            Log.d("Provera", "+123")
        }
        else {
            val pom = position -1

            if (pom >= 0) {
                position--
                setView()
            }
            else
              callBacks.back()
            Log.d("Provera", "-123")
        }
        Log.d("Provera", position.toString())
    }

    interface callBack{
        fun next()
        fun back()
    }
}