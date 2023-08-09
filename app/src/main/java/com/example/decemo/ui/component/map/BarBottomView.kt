package com.example.decemo.ui.component.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.model.Bar
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class BarBottomView(private val bar: Bar, val view: View, val context: Context, val onViewClick: (Bar) -> Unit) {
    private val sheet: BottomSheetDialog = BottomSheetDialog(context, R.style.SheetDialog)

    // TODO(Da se vuce odkenkuda)
    private val url = "https://bekmen.rs/api/slike/"

    init {
        sheet.setContentView(R.layout.bottom_shape)
        sheet.setCanceledOnTouchOutside(true)
        setView()
    }

    @SuppressLint("SetTextI18n")
    private fun setView() {
        val name = sheet.findViewById<TextView>(R.id.imeKafica)!!
        val image = sheet.findViewById<ImageView>(R.id.slikaLokala)!!
        val typeOfLocal = sheet.findViewById<TextView>(R.id.vrsta)!!
        val phone = sheet.findViewById<TextView>(R.id.telefon1)!!
        val address = sheet.findViewById<TextView>(R.id.adresa)!!
        val workTime = sheet.findViewById<TextView>(R.id.radnoVreme)!!
        val isOpen = sheet.findViewById<TextView>(R.id.vremePr)!!
        val barView = sheet.findViewById<LinearLayout>(R.id.otvoriKafic)!!

        typeOfLocal.text = bar.barType.type
        phone.text = bar.phoneNumber
        address.text = bar.address
        name.text = bar.name

        runCatching {
            val dayInWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            workTime.text = bar.workTime[dayInWeek - 1]
        }.onFailure {
            workTime.text = ""
        }

        Glide.with(context)
            .load(url + bar.mainPictureUrl)
            .optionalCircleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(image)

        if (!isOpen()) {
            isOpen.text = "Zatvoreno"
            isOpen.setTextColor(Color.RED)
        }
        barView.setOnClickListener {
            sheet.dismiss()
            onViewClick(bar)
        }
        sheet.show()
    }

    // TODO prebaciti u api
    private fun isOpen(): Boolean {
        val time = Calendar.getInstance()
        val dayInWeek = time.get(Calendar.DAY_OF_WEEK)
        val h: Int = time.get(Calendar.HOUR_OF_DAY)
        val m: Int = time.get(Calendar.MINUTE)
        val hm = h + m * 0.01
        return try {
            val workTimes = bar.workTime[dayInWeek - 1].split("-")
            val hm1 = workTimes[0].toInt().toDouble()
            val hm2 = workTimes[1].toInt().toDouble()
            if (hm1 <= hm && hm2 > hm) {
                true
            } else if (hm1 == hm2) {
                true
            } else if (hm1 > hm2 && hm > hm1) true else hm1 > hm2 && hm < hm1 && hm < hm2
        } catch (e: Exception) {
            false
        }
    }
}