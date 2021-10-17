package com.example.decemo.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.decemo.R
import com.example.decemo.model.FilterMap
import com.example.decemo.model.Lokal
import com.example.decemo.model.factory.FilterMapFactory
import com.example.decemo.ui.map.PocetnaMap
import com.example.decemo.ui.viewmodel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.util.zip.Deflater
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {
    lateinit var viewModel: HomeViewModel
    var mapView: MapView? = null
    lateinit var map: PocetnaMap
    lateinit var filterIcon: ImageView
    var mutableData:MutableLiveData<ArrayList<FilterMap>> = MutableLiveData()
    var listaLokala:ArrayList<Lokal> = ArrayList()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(requireActivity(), getString(R.string.mapbox_access_token));
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        mapView = view.findViewById(R.id.mapView)
        filterIcon = view.findViewById(R.id.mainFilterMapIcon)
        mapView?.onCreate(savedInstanceState)
        mutableData = MutableLiveData()
        CoroutineScope(Dispatchers.Default).launch{
            mutableData = MutableLiveData(FilterMapFactory.getList())
        }
       // mutableData = MutableLiveData(FilterMapFactory.getList())
        mapView?.let {
            map = PocetnaMap(requireContext(),requireView(), it)
        }
        viewModel.listaLokala.observe(viewLifecycleOwner, Observer {
            listaLokala = it
            mutableData.value?.let { lets->
                map.listaLokala = filterMapLokalList(lets,it)

            }
        })
        filterIcon.setOnClickListener(View.OnClickListener {
            BottomView(view,requireContext(), mutableData)
        })

        mutableData.observe(viewLifecycleOwner, Observer {

            mutableData.value?.let {
                map.listaLokala = filterMapLokalList(mutableData.value!!,listaLokala)

            }

        })
    }

    private fun filterMapLokalList(listFilter:ArrayList<FilterMap>,listaLokala:ArrayList<Lokal>):ArrayList<Lokal>{
        val listaLokalaFiltrirano:ArrayList<Lokal> = ArrayList()
       listaLokala.forEach { lokal->
            for (i in 0 until listFilter.size){
                if (lokal.vrsta == listFilter[i].vrsta){
                    if (listFilter[i].status){
                        listaLokalaFiltrirano.add(lokal)
                        break
                    }
                    break
                }
            }
        }
        return listaLokalaFiltrirano
    }

   private class BottomView(val view: View, val context: Context, val data:MutableLiveData<ArrayList<FilterMap>>):CompoundButton.OnCheckedChangeListener {
        private val sheet: BottomSheetDialog = BottomSheetDialog(context, R.style.SheetDialog)
        private val listaFiltera:ArrayList<Switch> = ArrayList()

       init {
            sheet.setContentView(R.layout.bottom_main_filter_map)
            sheet.setCanceledOnTouchOutside(true)
            setView()
        }

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        private fun setView() {

            data.value?.forEach {
                sheet.findViewById<Switch>(it.switchId)?.let { let->
                    let.setOnCheckedChangeListener(this)
                    let.isChecked = it.status
                    listaFiltera.add(let)
                }
            }
            sheet.show()
        }

       override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
           for (i in 0 until listaFiltera.size){
               if (buttonView == listaFiltera[i]) {
                   data.value?.let {
                       it[i].status = isChecked
                   }
                   break
               }
           }
           data.postValue(data.value)
       }
   }
    }