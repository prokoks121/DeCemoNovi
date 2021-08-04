package com.example.decemo.ui.epoxy.model

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.decemo.R
import java.lang.Exception
import java.util.*

@EpoxyModelClass(layout = R.layout.lokal_radno_vreme)

abstract class LokalRadnoVremeViewModel: EpoxyModelWithHolder<LokalRadnoVremeViewModel.ViewHolder>() {

    @EpoxyAttribute
    lateinit var type: String

    @EpoxyAttribute
     var day: Int = 0

    @EpoxyAttribute
    lateinit var clickListener: View.OnClickListener

    private val currentTime: Date = Calendar.getInstance().getTime()
    private var currentDay: Int = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        if(currentDay == 1)
            currentDay = 8
        holder.textRadnoVreme.text = type
        if (day == currentDay){
        if (daLiRadi()){
            holder.textStatus.text= "Otvoreno"
            holder.textStatus.setTextColor(Color.GREEN)
        }
        else{
            holder.textStatus.text= "Zatvoreno"
            holder.textStatus.setTextColor(Color.RED)
        } }else{
            holder.textStatus.text= ""
        }
        var dayStr = ""
        when(day){
            2 -> dayStr = "Pon:"
            3 -> dayStr = "Uto:"
            4 -> dayStr = "Sre:"
            5 -> dayStr = "Čet:"
            6 -> dayStr = "Pet:"
            7 -> dayStr = "Sub:"
            8 -> dayStr = "Ned:"
        }
        holder.textDay.text = dayStr
        holder.body.setOnClickListener(clickListener)
    }


    private fun daLiRadi(): Boolean {
        val h: Int = currentTime.getHours()
        val m: Int = currentTime.getMinutes()
        val hm = h + m * 0.01
        val listaVremena = type.split("-")
        try {
            val hm1 = listaVremena[0].toInt().toDouble()
            val hm2 = listaVremena[1].toInt().toDouble()
            return if (hm1 <= hm && hm2 > hm) {
                true
            } else if (hm1 == hm2) {
                true
            } else if (hm1 > hm2 && hm > hm1) true else hm1 > hm2 && hm < hm1 && hm < hm2
        }catch (e: Exception){
            Log.d("Provera","vreme greska")
            return false


        }
    }

    class ViewHolder(): EpoxyHolder(){
        lateinit var textRadnoVreme: TextView
        lateinit var textDay:TextView
        lateinit var textStatus: TextView
        lateinit var body: ConstraintLayout

        override fun bindView(itemView: View) {
            body = itemView.findViewById(R.id.epoxy_lokal_usluge)
            textRadnoVreme = itemView.findViewById(R.id.textRadnoVreme)
            textDay = itemView.findViewById(R.id.textDanRadnoVreme)
            textStatus = itemView.findViewById(R.id.statusRadnogVremena)
        }

    }
}