//package com.example.decemo.ui.epoxy.model
//
//import android.util.Log
//import android.view.View
//import com.airbnb.epoxy.*
//import com.example.decemo.R
//import java.util.*
//import kotlin.collections.ArrayList
//
//@EpoxyModelClass(layout = R.layout.epoxy_radno_vreme_lokal)
//
//abstract class RadnoVremeViewModel: EpoxyModelWithHolder<RadnoVremeViewModel.ViewHolder>(){
//
//
//    @EpoxyAttribute
//    lateinit var list: ArrayList<String>
//    @EpoxyAttribute
//    lateinit var callBack: Controller.callBack
//    @EpoxyAttribute
//     var showAll: Boolean = false
//
//    lateinit var data: Controller.data
//    lateinit var controller: Controller
//
//    override fun bind(holder: ViewHolder) {
//        super.bind(holder)
//        data = Controller.data(callBack ,list,showAll)
//        controller = Controller()
//        holder.epoxyRecyclerView.setController(controller)
//        controller.setData(data)
//    }
//
//    class ViewHolder:EpoxyHolder(){
//        lateinit var epoxyRecyclerView: EpoxyRecyclerView
//        override fun bindView(itemView: View) {
//           // epoxyRecyclerView = itemView.findViewById(R.id.radnoVremeLokalEpoxy)
//        }
//    }
//
//    class Controller: TypedEpoxyController<Controller.data>() {
//
//
//        override fun buildModels(data:data) {
//            val currentDay: Int = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
//            Log.d("Provera",currentDay.toString())
//            var i = 1
//            if (data.showAll){
//        data.list?.let {
//            it.forEach {
//                lokalRadnoVremeView {
//                    id(i++)
//                    type(it)
//                    clickListener(View.OnClickListener {
//                        data.callBack.onClick()
//                    })
//                    day(i)
//
//
//                }
//            }
//        }}
//           else{
//                lokalRadnoVremeView  {
//                    id(i)
//                    type(data.list?.get(currentDay-2))
//                    clickListener(View.OnClickListener {
//                        data.callBack.onClick()
//                    })
//                    day(currentDay)
//                }
//            }
//        }
//
//
//        interface callBack{
//           fun  onClick()
//        }
//
//        data class data(var callBack:callBack,
//                        var list: ArrayList<String>?,
//                        var showAll:Boolean
//        )
//
//    }
//
//
//
//}
