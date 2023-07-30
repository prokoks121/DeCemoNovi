package com.example.decemo.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.retrofit.dto.BarDto
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.example.decemo.ui.viewmodel.ReservationViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ReservationFragment : BaseFragment(R.layout.fragment_reservation) {
    private val URL = "https://bekmen.rs/api/slike/"
    private val viewModel: ReservationViewModel by viewModel()
    private lateinit var logo: ImageView
    private lateinit var barName: TextView
    private lateinit var barType: TextView
    private lateinit var date: TextView
    private lateinit var numOfReservations: TextInputEditText
    private lateinit var submitButton: Button
    private val dateFormat by lazy {
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logo = view.findViewById(R.id.slikaLokala)
        barName = view.findViewById(R.id.imeKafica)
        barType = view.findViewById(R.id.vrstaKafica)
        date = view.findViewById(R.id.datum)
        numOfReservations = view.findViewById(R.id.num_of_reservations_input)
        submitButton = view.findViewById(R.id.reservation_submit)

        viewModel.bar.observe(viewLifecycleOwner) {
            setBar(it)
        }

        date.text = dateFormat.format(Date().time)

        date.setOnClickListener {
            val constraintsBuilder = CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now()).build()
            val dataPicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Izaberite dan")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(constraintsBuilder)
                .build()
            dataPicker.show(parentFragmentManager, "CUSTOM_TAG")
            dataPicker.addOnPositiveButtonClickListener {
                date.text = dateFormat.format(it)
            }
        }

        submitButton.setOnClickListener {
            if (!date.text.isNullOrEmpty() && !numOfReservations.text.isNullOrEmpty()) {
                viewModel.onSubmitClick(date.text.toString(), numOfReservations.text.toString().toInt())
            }
        }
    }

    private fun setBar(bar: BarDto) {
        barName.text = bar.name
        barType.text = bar.barType.type
        loadImage(bar.mainPictureUrl)
    }

    private fun loadImage(imageUrl: String) {
        Glide.with(requireContext())
            .load(URL + imageUrl)
            .optionalCircleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(logo)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}