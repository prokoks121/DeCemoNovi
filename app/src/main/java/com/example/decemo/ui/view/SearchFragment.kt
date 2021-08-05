package com.example.decemo.ui.view

import android.content.Context
import android.os.Bundle
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
import com.example.decemo.model.Dogadjaj
import com.example.decemo.model.Lokal
import com.example.decemo.model.VrstaLokala
import com.example.decemo.ui.epoxy.controler.PretragaController
import com.example.decemo.repository.Repository
import com.example.decemo.ui.viewmodel.SearchViewModel

class SearchFragment : Fragment(), PretragaController.changeStatus {
    private lateinit var viewModel: SearchViewModel
    private lateinit var controler:PretragaController
    private lateinit var data:dataForController
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        data = dataForController(arrayListOf(), arrayListOf(),requireContext(),this, Repository.listaVrstaLokala)
        setEpoxy(view)
        setObservers()
        setRefresh(view)
    }

    override fun click(id: Int, vrsta: String) {
        Repository.listaVrstaLokala.forEach {
            it.status = id == it.id
        }
        filterList(vrsta,viewModel.listaLokala.value!!)
    }

    override fun onLokalClick(lokal: Lokal) {
        val action = SearchFragmentDirections.actionSearchToLokalFragment2(lokal)
        requireView().findNavController().navigate(action)
    }

    override fun onDogadjajTouch(dogadjaj: Dogadjaj,position:Int) {
        val lista =  ArrayList<ArrayList<Dogadjaj>>()
        lista.add(arrayListOf<Dogadjaj>(data.dogadjaji[0]))
        lista.add(arrayListOf<Dogadjaj>(data.dogadjaji[1],data.dogadjaji[2],data.dogadjaji[3]))
        lista.add(arrayListOf<Dogadjaj>(data.dogadjaji[4],data.dogadjaji[5]))
        val action = SearchFragmentDirections.actionSearchToStoryFragment(
            StoryFragment.Data(lista, position))
        requireView().findNavController().navigate(action)
    }


    fun currentTypeLokal():String{
        Repository.listaVrstaLokala.forEach {
            if(it.status)
                return it.vrsta
        }
        return Repository.listaVrstaLokala.get(0).vrsta
    }

    fun filterList(vrsta:String, list:ArrayList<Lokal>){
       val filtriranaLista = arrayListOf<Lokal>()
        list.forEach {
            if (it.vrsta == vrsta)
                filtriranaLista.add(it)
        }
       data.lokali = filtriranaLista
        controler.setData(data)
    }

    private fun setObservers(){
        viewModel.listaLokala.observe(viewLifecycleOwner, Observer {
            filterList(currentTypeLokal(), it)
        })

        viewModel.listaDogadjaja.observe(viewLifecycleOwner, Observer {
            data.dogadjaji = it
            controler.setData(data)
        })
    }

    private fun setEpoxy(view:View){
        val recyclerViewLokali = view.findViewById<EpoxyRecyclerView>(R.id.listaLokala)
        controler = PretragaController()
        recyclerViewLokali.setController(controler)
       controler.setData(data)
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

data class dataForController(
        var lokali:ArrayList<Lokal>,
        var dogadjaji: ArrayList<Dogadjaj>,
        var context:Context,
        var callBack:PretragaController.changeStatus,
        var listaVrteLokala: ArrayList<VrstaLokala>
)