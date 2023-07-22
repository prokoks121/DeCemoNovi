package com.example.decemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.decemo.R
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.retrofit.dto.BarEvent
import com.example.decemo.retrofit.dto.BarTypeDto
import com.example.decemo.retrofit.dto.EventDto
import com.example.decemo.ui.epoxy.controler.SearchController
import com.example.decemo.ui.epoxy.controler.listeners.SearchControllerListener
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.example.decemo.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment(R.layout.fragment_search), SearchControllerListener {
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var controller: SearchController
    private var bars: List<BarDto> = listOf()
    private var barTypes: List<BarTypeDto> = listOf()
    private var events: List<BarEvent> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        setEpoxy(view)
        setObservers()
        setRefresh(view)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onBarTypeClick(barType: BarTypeDto) {
        viewModel.onBarTypeClick(barType)
    }

    override fun onBarClick(bar: BarDto) {
        viewModel.onBarClick(bar)
    }

    override fun onEventClick(events: List<BarEvent>, position:Int) {
        viewModel.onEventClick(events, position)
    }

    override fun onSearchClick(search: AutoCompleteTextView) {
        viewModel.onSearchClick(search)
    }

    private fun setObservers() {
        viewModel.bars.observe(viewLifecycleOwner) {
            bars = it
            epoxyControllerNotify()
        }

        viewModel.events.observe(viewLifecycleOwner) {
            events = it
            epoxyControllerNotify()
        }

        viewModel.barTypes.observe(viewLifecycleOwner) {
            barTypes = it
            epoxyControllerNotify()
        }
    }

    private fun epoxyControllerNotify() {
        controller.setData(bars.toMutableList(), events.toMutableList(), barTypes.toMutableList())
    }

    private fun setEpoxy(view: View) {
        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.listaLokala)
        controller = SearchController(requireContext(), this)
        epoxyRecyclerView.setController(controller)
    }

    private fun setRefresh(view: View) {
        val swipeToRefresh = view.findViewById<SwipeRefreshLayout>(R.id.refresh)
        swipeToRefresh.setOnRefreshListener {
            viewModel.onRefresh()
            swipeToRefresh.isRefreshing = false
        }
    }
}