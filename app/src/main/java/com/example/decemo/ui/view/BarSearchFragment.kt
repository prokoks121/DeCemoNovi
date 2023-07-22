package com.example.decemo.ui.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.decemo.R
import com.example.decemo.ui.epoxy.controler.BarSearchController
import com.example.decemo.ui.viewmodel.BarSearchViewModel
import com.example.decemo.ui.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class BarSearchFragment : BaseFragment(R.layout.fragment_search_lokali) {
    private lateinit var view: View
    private val barSearchViewModel: BarSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.search_lokali_epoxy)
        val epoxyController = BarSearchController(context = requireContext())
        epoxyRecyclerView.setController(epoxyController)
        val editText = view.findViewById<AutoCompleteTextView>(R.id.search2)
        openSoftKeyboard(requireContext(), editText)

        editText.addTextChangedListener(
            onTextChanged = { text, _, _, _ ->
                barSearchViewModel.searchBar(text.toString())
            }
        )

        barSearchViewModel.bars.observe(viewLifecycleOwner) {
            epoxyController.setData(it)
        }

        epoxyController.setOnBarClick {
            hideKeyboard()
            barSearchViewModel.onBarClick(it)
        }
    }

    override fun getViewModel(): BaseViewModel {
        return barSearchViewModel
    }

    private fun openSoftKeyboard(context: Context, view: View) {
        view.requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}