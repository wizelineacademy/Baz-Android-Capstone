package com.axiasoft.android.zerocoins.ui.features.available_books.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.application.ZeroCoinsApplication
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.databinding.FragmentBookOrderListBinding
import com.axiasoft.android.zerocoins.network.app.InternetConnectionAvailableLiveData
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.AvailableBooksViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.BookOrderViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.views.adapters.BookOrderAdapter

class BookOrderListFragment : Fragment(R.layout.fragment_book_order_list) {

    private var columnCount = 1

    lateinit var fragmentBinding: FragmentBookOrderListBinding

    //private val viewModel: BooksScreenViewModel by viewModels()
    lateinit var availableBooksViewModel: AvailableBooksViewModel
    lateinit var bookOrderViewModel: BookOrderViewModel

    val bookOrderAdapter = BookOrderAdapter { bookOrderSelected ->
        log("z0", "selected $bookOrderSelected")
        availableBooksViewModel.selectedBookOrder = bookOrderSelected
        bookOrderViewModel.selectedBookOrder = bookOrderSelected
        navigateToTicker()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentBookOrderListBinding.inflate(inflater, container, false)
        fragmentBinding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookOrderAdapter
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModels()
        initObservers()
        initUIListeners()
        //TODO fix first time, observer doesnt update internet state
        refreshData()
    }

    fun initViewModels() {
        availableBooksViewModel =
            ViewModelProvider(requireActivity()).get(AvailableBooksViewModel::class.java)

        bookOrderViewModel = ViewModelProvider(requireActivity()).get(BookOrderViewModel::class.java)
    }

    var connection = true

    fun initObservers() {
        availableBooksViewModel.books.observe(viewLifecycleOwner) {
            bookOrderAdapter.updateBookOrders(it)
        }
        InternetConnectionAvailableLiveData(requireActivity().application)
            .observe(viewLifecycleOwner) { isConnected ->
                //bookOrderViewModel.isInternetAvailable = isConnected
                connection = isConnected
                log("z0","connection fragment isConnected $connection")
                if (!isConnected){
                    Toast.makeText(requireContext(),"Sin internet",Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun initUIListeners() {
        fragmentBinding.apply {
            fabRefresh.setOnClickListener {
                refreshData()
            }
        }
    }

    fun refreshData() {
        if (connection){//true){//bookOrderViewModel.isInternetAvailable) {
            log("z0", "remote")
            availableBooksViewModel.getExchangeOrderBooks()
        } else {
            log("z0", "local")
            availableBooksViewModel.getLocalExchangeOrderBooks()
        }
    }

    fun navigateToTicker() {
        val fragment = TickerFragment()
        val fm: FragmentManager = requireActivity().supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.cl_cointainer, fragment)
        ft.addToBackStack(TickerFragment.TAG)
        ft.commit()
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        const val TAG = "BookOrderListFragment"

        @JvmStatic
        fun newInstance(columnCount: Int = 1) =
            BookOrderListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}