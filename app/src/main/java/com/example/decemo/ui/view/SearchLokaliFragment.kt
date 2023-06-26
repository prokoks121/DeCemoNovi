//package com.example.decemo.ui.view
//
//import android.app.Activity
//import android.content.Context
//import android.os.Bundle
//import android.transition.TransitionInflater
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.inputmethod.InputMethodManager
//import android.widget.AutoCompleteTextView
//import androidx.core.widget.addTextChangedListener
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.findNavController
//import androidx.navigation.fragment.navArgs
//import com.airbnb.epoxy.EpoxyRecyclerView
//import com.example.decemo.R
//import com.example.decemo.model.Lokal
//import com.example.decemo.ui.epoxy.controler.SearchLokaliController
//import com.example.decemo.ui.viewmodel.SearchLokaliViewModel
//import kotlinx.coroutines.launch
//
//
//class SearchLokaliFragment : Fragment(), SearchLokaliController.CallBack {
//
//    private val args: SearchLokaliFragmentArgs by navArgs()
//    private var vieww: View? = null
//    private lateinit var viewModel: SearchLokaliViewModel
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        vieww = inflater.inflate(R.layout.fragment_search_lokali, container, false)
//        return vieww
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity()).get(SearchLokaliViewModel::class.java)
//        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.search_lokali_epoxy)
//        val epoxyController = SearchLokaliController(context = requireContext(), this)
//        epoxyRecyclerView.setController(epoxyController)
//        val editText = view.findViewById<AutoCompleteTextView>(R.id.search2)
//        openSoftKeyboard(requireContext(), editText)
//
//        editText.addTextChangedListener(
//            onTextChanged = { text, start, before, count ->
//                viewLifecycleOwner.lifecycleScope.launch {
//                    viewModel.setFilteredList(filter(text.toString()))
//
//                }
//
//            }
//        )
//
//        viewModel.filterListLokala.observe(viewLifecycleOwner, Observer {
//            epoxyController.listaLokala = it
//        })
//
//
//    }
//
//    fun openSoftKeyboard(context: Context, view: View) {
//        view.requestFocus()
//        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
//    }
//
//
//    suspend fun filter(input: String): ArrayList<Lokal> {
//        val filterList = arrayListOf<Lokal>()
//        viewModel.listaLokala.value?.forEach {
//            if (it.ime.contains(input))
//                filterList.add(it)
//        }
//        return filterList
//    }
//
//    override fun onLokalClick(lokal: Lokal) {
//
//        vieww?.let { requireContext().hideKeyboard(it) }
//        val action = SearchLokaliFragmentDirections.actionSearchLokaliFragmentToLokalFragment(lokal)
//        requireView().findNavController().navigate(action)
//    }
//
//    fun Context.hideKeyboard(view: View) {
//        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//    }
//
//}