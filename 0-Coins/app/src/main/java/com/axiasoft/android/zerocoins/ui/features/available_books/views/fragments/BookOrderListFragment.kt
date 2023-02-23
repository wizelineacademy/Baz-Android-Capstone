package com.axiasoft.android.zerocoins.ui.features.available_books.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.databinding.FragmentBookOrderListBinding
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.AvailableBooksViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.BookOrderViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.views.adapters.BookOrderAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookOrderListFragment : Fragment(R.layout.fragment_book_order_list) {

    private var columnCount = 1

    lateinit var fragmentBinding: FragmentBookOrderListBinding

    private val availableBooksViewModel by viewModels<AvailableBooksViewModel>()
    lateinit var bookOrderViewModel: BookOrderViewModel

    private val bookOrderAdapter = BookOrderAdapter { bookOrderSelected ->
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
    }

    fun initViewModels() {
        bookOrderViewModel =
            ViewModelProvider(requireActivity()).get(BookOrderViewModel::class.java)
    }

    fun initObservers() {
        availableBooksViewModel.books.observe(viewLifecycleOwner) {
            bookOrderAdapter.updateBookOrders(it)
        }

        bookOrderViewModel.internetStatus.observe(viewLifecycleOwner) { isConnected ->
            log("z0", "internetStatus observer isConnected $isConnected")
            if (!isConnected) {
                Toast.makeText(requireContext(), "Sin internet", Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                refreshData()
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
        if (bookOrderViewModel.internetStatus.isNetworkAvailable()){
            log("z0", "Orders books fetch remote")
            availableBooksViewModel.getExchangeOrderBooks()
        } else {
            log("z0", "Orders books fetch local")
            availableBooksViewModel.getLocalExchangeOrderBooks()
        }
    }

    fun navigateToTicker() {
        val navController = findNavController()
        navController.navigate(R.id.tickerFragment)
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