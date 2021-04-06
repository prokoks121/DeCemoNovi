package com.example.decemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.decemo.R
import com.example.decemo.ui.epoxy.controler.PretragaController
import com.example.decemo.repository.Repository
import com.example.decemo.ui.viewmodel.SearchViewModel

class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        val recyclerViewLokali = view.findViewById<EpoxyRecyclerView>(R.id.listaLokala)
        val controler = PretragaController(requireContext())
        val swipRefresh = view.findViewById<SwipeRefreshLayout>(R.id.refresh)
        recyclerViewLokali.setController(controler)
        controler.listaVrteLokala = Repository.listaVrstaLokala
        viewModel.listaLokala.observe(viewLifecycleOwner, Observer {
            controler.listaLokala = it
        })
        viewModel.listaDogadjaja.observe(viewLifecycleOwner, Observer {
            controler.listaDogadjaj = it
        })
        swipRefresh.setOnRefreshListener {
            viewModel.getListuKafica()
            viewModel.getListuDogadjaja()
            swipRefresh.isRefreshing = false
        }
    }
}