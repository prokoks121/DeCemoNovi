package com.example.decemo.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.airbnb.epoxy.*
import com.example.decemo.R
import com.example.decemo.retrofit.dto.BarEvent
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.example.decemo.ui.viewmodel.StoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoryFragment : BaseFragment(R.layout.fragment_story), StoryViewFragment.StoryController {

    private val storyViewModel: StoryViewModel by viewModel()
    private lateinit var viewPager: ViewPager

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.SrotiesPager)
        val pagerAdapter = ViewPagerAdapter(parentFragmentManager, this)
        pagerAdapter.setEvents(storyViewModel.barEvents, storyViewModel.startPosition)
        viewPager.adapter = pagerAdapter
    }

    override fun getViewModel(): BaseViewModel {
        return storyViewModel
    }

    private class ViewPagerAdapter(fm: FragmentManager, private val storyController:StoryViewFragment.StoryController) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

        private var events: List<BarEvent> = arrayListOf()

        private var position: Int = 0

        fun setEvents(events: List<BarEvent>, position: Int) {
            this.events = events
            this.position = position
            notifyDataSetChanged()
        }

        override fun getCount(): Int {
            return events.size
        }

        override fun getItem(position: Int): Fragment {
            return StoryViewFragment(events[position], storyController)
        }
    }

    override fun next() {
        if (viewPager.currentItem > viewPager.childCount)
            requireView().findNavController().popBackStack()
        viewPager.currentItem = viewPager.currentItem + 1
    }

    override fun back() {
        viewPager.currentItem = viewPager.currentItem - 1
    }
}