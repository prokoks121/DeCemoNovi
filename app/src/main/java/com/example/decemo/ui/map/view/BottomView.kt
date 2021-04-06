package com.example.decemo.ui.map.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.model.Lokal
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomView(private val kafic: Lokal, val context: Context) {
    private val sheet: BottomSheetDialog = BottomSheetDialog(context, R.style.SheetDialog)
    private val url = "https://api.polovnitelefoni.net/slike/"

    init {
        sheet.setContentView(R.layout.bottom_shape)
        sheet.setCanceledOnTouchOutside(true)
        setView()
    }

    @SuppressLint("SetTextI18n")
    private fun setView() {
        val ime = sheet.findViewById<TextView>(R.id.imeKafica)
        val slika = sheet.findViewById<ImageView>(R.id.slikaLokala)
        val vrsta = sheet.findViewById<TextView>(R.id.vrsta)
        val telefon1 = sheet.findViewById<TextView>(R.id.telefon1)
        val adresa = sheet.findViewById<TextView>(R.id.adresa)
        // val vreme = sheet.findViewById<TextView>(R.id.radnoVreme)
        val radnoPr = sheet.findViewById<TextView>(R.id.vremePr)
        val otvoriKafic = sheet.findViewById<LinearLayout>(R.id.otvoriKafic)
        vrsta!!.text = kafic.vrsta
        telefon1!!.text = kafic.telefon
        adresa!!.text = kafic.adresa
        ime!!.text = kafic.ime
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
            //   val intent = Intent(context, com.example.decemo.kafic::class.java)
            //  intent.putExtra("Kafic", kafic as Parcelable)
            //  context.startActivity(intent)
        }
        sheet.show()
    }

    private fun daLiRadi(): Boolean {
        return true
    }
}