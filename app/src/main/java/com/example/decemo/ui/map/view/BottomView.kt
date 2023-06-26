//package com.example.decemo.ui.map.view
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.graphics.Color
//import android.util.Log
//import android.view.View
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.navigation.findNavController
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.example.decemo.R
//import com.example.decemo.model.Lokal
//import com.example.decemo.ui.view.HomeFragmentDirections
//import com.google.android.material.bottomsheet.BottomSheetDialog
//import java.util.*
//
//class BottomView(private val local: Lokal, val view: View, val context: Context) {
//    private val sheet: BottomSheetDialog = BottomSheetDialog(context, R.style.SheetDialog)
//    // TODO(Da se vuce odkenkuda)
//    private val url = "https://bekmen.rs/api/slike/"
//    // TODO(Prebaciti u fuknciju)
//    private val currentTime: Date = Calendar.getInstance().time
//    private var currentDay: Int = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
//
//    init {
//        sheet.setContentView(R.layout.bottom_shape)
//        sheet.setCanceledOnTouchOutside(true)
//        if (currentDay == 1)
//            currentDay = 8
//        setView()
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun setView() {
//        val name = sheet.findViewById<TextView>(R.id.imeKafica)!!
//        val image = sheet.findViewById<ImageView>(R.id.slikaLokala)!!
//        val typeOfLocal = sheet.findViewById<TextView>(R.id.vrsta)!!
//        val phone = sheet.findViewById<TextView>(R.id.telefon1)!!
//        val address = sheet.findViewById<TextView>(R.id.adresa)!!
//        val workTime = sheet.findViewById<TextView>(R.id.radnoVreme)!!
//        val isOpen = sheet.findViewById<TextView>(R.id.vremePr)!!
//        val localView = sheet.findViewById<LinearLayout>(R.id.otvoriKafic)!!
//
//        typeOfLocal.text = local.vrsta
//        phone.text = local.telefon
//        address.text = local.adresa
//        name.text = local.ime
//        try {
//            workTime.text = local.radno[currentDay - 2]
//        } catch (e: Exception) {
//            workTime.text = ""
//        }
//
//        Glide.with(context)
//            .load(url + local.slika)
//            .optionalCircleCrop()
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .into(image)
//
//        if (!isOpen()) {
//            // TODO(U konstantu)
//            isOpen.text = "Zatvoreno"
//            isOpen.setTextColor(Color.RED)
//        }
//        localView.setOnClickListener {
//            val action = HomeFragmentDirections.actionHome2ToLokalFragment(local)
//            view.findNavController().navigate(action)
//            sheet.hide()
//        }
//        sheet.show()
//    }
//
//    private fun isOpen(): Boolean {
//        val h: Int = currentTime.hours
//        val m: Int = currentTime.minutes
//        val hm = h + m * 0.01
//        return try {
//            val workTimes = local.radno[currentDay - 2].split("-")
//            val hm1 = workTimes[0].toInt().toDouble()
//            val hm2 = workTimes[1].toInt().toDouble()
//            if (hm1 <= hm && hm2 > hm) {
//                true
//            } else if (hm1 == hm2) {
//                true
//            } else if (hm1 > hm2 && hm > hm1) true else hm1 > hm2 && hm < hm1 && hm < hm2
//        } catch (e: Exception) {
//            false
//        }
//    }
//}