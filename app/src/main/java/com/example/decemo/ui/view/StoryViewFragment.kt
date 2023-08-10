package com.example.decemo.ui.view

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.decemo.R
import com.example.decemo.base.BASE_IMAGES_URL
import com.example.decemo.base.EVENT_URL
import com.example.decemo.model.BarEvent

class StoryViewFragment(private val barEvent: BarEvent, private val callBacks: StoryController) : Fragment() {

    private lateinit var storyImageBackground: ImageView
    lateinit var barImage: ImageView
    lateinit var barName: TextView
    lateinit var layout: ConstraintLayout
    var position = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.epoxy_story, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        position = 0
        storyImageBackground = view.findViewById(R.id.StoryImageBackground)
        barImage = view.findViewById(R.id.StoryLocalImage)
        barName = view.findViewById(R.id.StoryLokalName)
        layout = view.findViewById(R.id.StoryFragment)
        val display: Display = requireActivity().windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        setView()

        layout.setOnTouchListener { _, event ->
            val x = event!!.x.toInt()
            when (event.action) {
                MotionEvent.ACTION_UP -> storyMove(width.toDouble(), x.toDouble())
            }
            true
        }
    }

    private fun setView() {

        Glide.with(requireActivity())
            .load(EVENT_URL + barEvent.events[position].imageUrl)
            .transform(CenterCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(storyImageBackground)


        Glide.with(requireActivity())
            .load(BASE_IMAGES_URL + barEvent.barImageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .circleCrop()
            .into(barImage)

        barName.text = barEvent.barName
    }

    private fun storyMove(width: Double, x: Double) {
        if (width / x < 2) {
            val pom = position + 1
            if (pom < barEvent.events.size) {
                position++
                setView()
            } else
                callBacks.next()
        } else {
            val pom = position - 1

            if (pom >= 0) {
                position--
                setView()
            } else
                callBacks.back()
        }
    }

    interface StoryController {
        fun next()
        fun back()
    }
}