package com.example.decemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.decemo.R
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseFragment(private val layoutResId: Int) : Fragment() {
    companion object {
        private const val DISAPPEAR_BOX_DURATION = 2000L
    }

    private lateinit var successView: TextView
    private lateinit var errorView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val baseView: View = inflater.inflate(R.layout.fragment_base, container, false)
        val layoutView: View = inflater.inflate(layoutResId, container, false)
        baseView.findViewById<FrameLayout>(R.id.content).addView(layoutView)
        successView = baseView.findViewById(R.id.successMessage)
        errorView = baseView.findViewById(R.id.errorMessage)
        return baseView
    }

    private fun showInfoDisappearedBox(message: String) {
        successView.text = message
        successView.visibility = VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            delay(DISAPPEAR_BOX_DURATION)
            launch(Dispatchers.Main) {
                successView.visibility = INVISIBLE
            }
        }
    }

    private fun showErrorDisappearedBox(message: String) {
        errorView.text = message
        errorView.visibility = VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            delay(DISAPPEAR_BOX_DURATION)
            launch(Dispatchers.Main) {
                errorView.visibility = INVISIBLE
            }
        }
    }

    private fun showErrorDialog(message: String) {
        showDialog("Greska", message, false){
            onBackPress()
        }
    }

    private fun onBackPress() {
        parentFragmentManager.popBackStack()
    }

    fun showDialog(title: String, message: String, showNegativeButton: Boolean, onPositiveClick: () -> Unit) {
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
        if (showNegativeButton) {
            dialog.setNegativeButton("Odustani") { _, _ -> }
        }
        dialog.setPositiveButton("Potvrdi") { _, _ ->
            onPositiveClick()
        }
        dialog.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().onViewCreated()
        observeBaseViewModel()
    }

    override fun onResume() {
        super.onResume()
        getViewModel().onViewResumed()
    }

    override fun onPause() {
        super.onPause()
        getViewModel().onViewPaused()
    }

    private val onBackPressObserve: (Unit?) -> Unit = {
        if (it != null) {
            onBackPress()
            getViewModel().clearOnBackPress()
        }
    }

    private val errorMessageObserve: (String?) -> Unit = {
        if (!it.isNullOrEmpty()) {
            showErrorDisappearedBox(it)
            getViewModel().clearErrorMessage()
        }
    }

    private val errorMessageDialogObserve: (String?) -> Unit = {
        if (!it.isNullOrEmpty()) {
            showErrorDialog(it)
            getViewModel().clearErrorMessageDialog()
        }
    }

    private val successMessageObserve: (String?) -> Unit = {
        if (!it.isNullOrEmpty()) {
            showInfoDisappearedBox(it)
            getViewModel().clearSuccessMessage()
        }
    }

    private fun observeBaseViewModel() {
        getViewModel().onBackPress.observe(viewLifecycleOwner, onBackPressObserve)
        getViewModel().errorMessage.observe(viewLifecycleOwner, errorMessageObserve)
        getViewModel().errorMessageDialog.observe(viewLifecycleOwner, errorMessageDialogObserve)
        getViewModel().successMessage.observe(viewLifecycleOwner, successMessageObserve)
    }

    abstract fun getViewModel(): BaseViewModel
}