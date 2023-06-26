//package com.example.decemo.ui.epoxy.model
//
//import android.content.Context
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.airbnb.epoxy.Carousel
//import com.airbnb.epoxy.ModelView
//
//
//@ModelView(saveViewState = false,autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
//
//class StoryCarousel(contexts: Context):Carousel(contexts) {
//
//    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
//        super.setPadding(0, 0, 0, 0)
//    }
//
//    override fun createLayoutManager(): LayoutManager {
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        layoutManager.scrollToPosition(1)
//        return layoutManager
//    }
//}