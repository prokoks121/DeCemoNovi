package com.example.decemo.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.decemo.R
import com.example.decemo.model.Lokal
import com.example.decemo.model.VrstaLokala
import com.example.decemo.ui.epoxy.controler.PretragaController
import com.example.decemo.repository.Repository
import com.example.decemo.ui.viewmodel.LokalViewModel
import com.example.decemo.ui.viewmodel.SearchViewModel

class SearchFragment : Fragment(), PretragaController.changeStatus {
    private lateinit var viewModel: SearchViewModel
    private lateinit var lokalViewModel: LokalViewModel
  //  private lateinit var view:View

    private lateinit var controler:PretragaController
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        lokalViewModel = ViewModelProvider(requireActivity()).get(LokalViewModel::class.java)
        setEpoxy(view)
        setObservers()
        setRefresh(view)
    }
    override fun click(id: Int, vrsta: String) {
        Repository.listaVrstaLokala.forEach {
            it.status = id == it.id
        }
        filterList(vrsta,viewModel.listaLokala.value!!)
        controler.requestModelBuild()
    }

    override fun onLokalClick(lokal: Lokal) {
        lokalViewModel.setLokal(lokal)
        requireView().findNavController().navigate(R.id.action_search_to_lokalFragment)

    }

    fun filterList(vrsta:String, list:ArrayList<Lokal>){
       val filtriranaLista = arrayListOf<Lokal>()
        list.forEach {
            if (it.vrsta == vrsta)
                filtriranaLista.add(it)
        }
        controler.listaLokala = filtriranaLista
    }

    private fun setObservers(){
        viewModel.listaLokala.observe(viewLifecycleOwner, Observer {
            controler.listaLokala = it
        })
        viewModel.listaDogadjaja.observe(viewLifecycleOwner, Observer {
            controler.listaDogadjaj = it
        })
    }
    private fun setEpoxy(view:View){
        val recyclerViewLokali = view.findViewById<EpoxyRecyclerView>(R.id.listaLokala)
        controler = PretragaController(requireContext(), this)
        recyclerViewLokali.setController(controler)
        controler.listaVrteLokala = Repository.listaVrstaLokala
    }
    private fun setRefresh(view:View){
        val swipRefresh = view.findViewById<SwipeRefreshLayout>(R.id.refresh)
        swipRefresh.setOnRefreshListener {
            viewModel.getListuKafica()
            viewModel.getListuDogadjaja()
            swipRefresh.isRefreshing = false
        }
    }


}