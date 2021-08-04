package com.example.decemo.ui.map.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.model.Lokal
import com.example.decemo.ui.view.HomeFragment
import com.example.decemo.ui.view.HomeFragmentDirections
import com.example.decemo.ui.view.SearchFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.lang.Exception
import java.util.*

class BottomView(private val kafic: Lokal, val view: View, val context: Context) {
    private val sheet: BottomSheetDialog = BottomSheetDialog(context, R.style.SheetDialog)
    private val url = "https://api.polovnitelefoni.net/slike/"
    private val currentTime: Date = Calendar.getInstance().getTime()
    private var currentDay: Int = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    init {
        sheet.setContentView(R.layout.bottom_shape)
        sheet.setCanceledOnTouchOutside(true)
        if(currentDay == 1)
            currentDay = 8
        setView()
    }

    @SuppressLint("SetTextI18n")
    private fun setView() {
        val ime = sheet.findViewById<TextView>(R.id.imeKafica)
        val slika = sheet.findViewById<ImageView>(R.id.slikaLokala)
        val vrsta = sheet.findViewById<TextView>(R.id.vrsta)
        val telefon1 = sheet.findViewById<TextView>(R.id.telefon1)
        val adresa = sheet.findViewById<TextView>(R.id.adresa)
        val vreme = sheet.findViewById<TextView>(R.id.radnoVreme)
        val radnoPr = sheet.findViewById<TextView>(R.id.vremePr)
        val otvoriKafic = sheet.findViewById<LinearLayout>(R.id.otvoriKafic)
        vrsta!!.text = kafic.vrsta
        telefon1!!.text = kafic.telefon
        adresa!!.text = kafic.adresa
        ime!!.text = kafic.ime
        try {
            vreme!!.text = kafic.radno[currentDay-2]
        }catch (e: Exception){
            vreme!!.text = ""

        }
        Glide.with(context)
                .load(url + kafic.slika)
                .optionalCircleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(slika!!)



        if (!daLiRadi()) {
            radnoPr!!.text = "Zatvoreno"
            radnoPr.setTextColor(Color.RED)
        }
        otvoriKafic!!.setOnClickListener {
           val action =  HomeFragmentDirections.actionHome2ToLokalFragment(kafic)
            view.findNavController().navigate(action)
            sheet.hide()

        }
        sheet.show()
    }

    private fun daLiRadi(): Boolean {
        val h: Int = currentTime.getHours()
        val m: Int = currentTime.getMinutes()
        val hm = h + m * 0.01
        try {
            val listaVremena = kafic.radno.get(currentDay-2).split("-")
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
}