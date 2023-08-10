package com.example.decemo.ui.view

import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.core.view.doOnPreDraw
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.decemo.R
import com.example.decemo.model.Bar
import com.example.decemo.model.BarEvent
import com.example.decemo.model.BarType
import com.example.decemo.ui.epoxy.controler.SearchController
import com.example.decemo.ui.epoxy.controler.listeners.SearchControllerListener
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.example.decemo.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment(R.layout.fragment_search), SearchControllerListener {
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var controller: SearchController
    private var bars: List<Bar> = listOf()
    private var barTypes: List<BarType> = listOf()
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

    override fun onBarTypeClick(barType: BarType) {
        viewModel.onBarTypeClick(barType)
    }

    override fun onBarClick(bar: Bar) {
        viewModel.onBarClick(bar)
    }

    override fun onEventClick(events: List<BarEvent>, position: Int) {
        viewModel.onEventClick(events, position)
    }

    override fun onSearchClick(search: AutoCompleteTextView) {
        viewModel.onSearchClick(search)
    }

    private fun setObservers() {
        viewModel.bars.observe(viewLifecycleOwner) {
            bars = it
            controller.setBars(it)
        }

        viewModel.events.observe(viewLifecycleOwner) {
            events = it
            controller.setEvents(it)
        }

        viewModel.barTypes.observe(viewLifecycleOwner) {
            barTypes = it
            controller.setBarTypes(it)
        }
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