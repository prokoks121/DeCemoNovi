package com.example.decemo.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.airbnb.epoxy.*
import com.example.decemo.R
import com.example.decemo.model.Dogadjaj
import com.example.decemo.ui.viewmodel.StoryViewModel
import java.io.Serializable

class StoryFragment : Fragment(), StoryViewFragment.callBack {

    private lateinit var viewModel:StoryViewModel
    private val data:StoryFragmentArgs by navArgs()
    private lateinit var mPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_story, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(StoryViewModel::class.java)
        viewModel.data.postValue(data.Data)
        mPager = view.findViewById(R.id.SrotiesPager)

        viewModel.data.observe(viewLifecycleOwner, Observer {data->
            val pagerAdapter = fragmentManager?.let { ViewPagerAdapter(it,data,this) }

            mPager.adapter = pagerAdapter
            mPager.setCurrentItem(data.position)

        })
}
    data class Data(
        var dogadjaj: ArrayList<ArrayList<Dogadjaj>>,
        var position:Int
    ):Serializable

private class ViewPagerAdapter(fm: FragmentManager,var data:Data,val callback:StoryViewFragment.callBack) : FragmentStatePagerAdapter(fm){
    override fun getCount(): Int {
       return data.dogadjaj.size
    }
    override fun getItem(position: Int): Fragment{
        return StoryViewFragment(data.dogadjaj[position],callback)
    }
}
    override fun next() {
        mPager.setCurrentItem(mPager.currentItem +1)
    }

    override fun back() {
        mPager.setCurrentItem(mPager.currentItem -1)
    }

}