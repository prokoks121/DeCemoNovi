package com.example.decemo.ui.component

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.Switch
import com.example.decemo.R
import com.example.decemo.model.MapFilter
import com.google.android.material.bottomsheet.BottomSheetDialog

class FilterBottomView(val view: View, val context: Context, private val mapFilters: List<MapFilter>) {
    private val sheet: BottomSheetDialog = BottomSheetDialog(context, R.style.SheetDialog)

    init {
        sheet.setContentView(R.layout.bottom_main_filter_map)
        sheet.setCanceledOnTouchOutside(true)
        setView()
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode", "MissingInflatedId")
    private fun setView() {
        mapFilters.forEach { filter ->
            val switchLayout = LayoutInflater.from(context).inflate(R.layout.component_switch_filter, null)
            val switch = switchLayout.findViewById<Switch>(R.id.filter_switch_component)
            switch.text = filter.type
            switch.isChecked = filter.status
            switch.setOnCheckedChangeListener { _, status ->
                onSwitch(filter, status)
            }
            val grid = sheet.findViewById<GridLayout>(R.id.filterBottomGrid)!!
            grid.addView(switchLayout)
        }
        sheet.show()
    }

    fun setOnSwitchListener(onSwitch: (filter: MapFilter, newStatus: Boolean) -> Unit) {
        this.onSwitch = onSwitch
    }

    private var onSwitch: (filter: MapFilter, newStatus: Boolean) -> Unit = { _, _ -> }
}