package com.example.decemo.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.decemo.R
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.example.decemo.ui.viewmodel.ReservationUpdateViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class ReservationUpdateFragment : BaseFragment(R.layout.fragment_reservation_update) {
    private val URL = "https://bekmen.rs/api/slike/"
    private val viewModel: ReservationUpdateViewModel by viewModel()
    private lateinit var logo: ImageView
    private lateinit var barName: TextView
    private lateinit var date: TextView
    private lateinit var numOfReservations: TextInputEditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button

    private val dateFormat by lazy {
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logo = view.findViewById(R.id.slikaLokala)
        barName = view.findViewById(R.id.imeKafica)
        date = view.findViewById(R.id.datum)
        numOfReservations = view.findViewById(R.id.num_of_reservations_input)
        updateButton = view.findViewById(R.id.reservation_update)
        deleteButton = view.findViewById(R.id.reservation_delete)
        date.text = dateFormat.format(Date().time)

        viewModel.reservation.observe(viewLifecycleOwner) {
            barName.text = it.barName
            loadImage(it.barImage)
            date.text = it.reservationDay
            numOfReservations.setText(it.numOfPersons.toString())
        }

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

        updateButton.setOnClickListener {
            if (!date.text.isNullOrEmpty() && !numOfReservations.text.isNullOrEmpty()) {
                showDialog("Azuriraj rezervaciju", "Da li ste sigurni da zelite da azurirate rezervaciju", true) {
                    viewModel.onUpdateClick(date.text.toString(), numOfReservations.text.toString().toInt())
                }
            }
        }

        deleteButton.setOnClickListener {
            showDialog("Obrisite rezervaciju", "Da li ste sigurni da zelite da obrisete rezervaciju", true) {
                viewModel.onDeleteClick()
            }
        }
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