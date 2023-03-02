package com.axiasoft.android.zerocoins.ui.features.available_books.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.databinding.FragmentBookOrderListBinding
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.AvailableBooksViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.BookOrderViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.views.adapters.BookOrderAdapter
import com.axiasoft.android.zerocoins.ui.features.available_books.views.compose.ExchangeOrderBookList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookOrderListFragment : Fragment(R.layout.fragment_book_order_list) {

    private var columnCount = 1

    private val useCompose = true
    private val useRX = true

    private lateinit var fragmentBinding: FragmentBookOrderListBinding

    private val availableBooksViewModel by viewModels<AvailableBooksViewModel>()
    lateinit var bookOrderViewModel: BookOrderViewModel

    private val bookOrderAdapter = BookOrderAdapter { bookOrderSelected ->
        log("z0", "selected $bookOrderSelected")
        availableBooksViewModel.selectedBookOrder = bookOrderSelected
        bookOrderViewModel.selectedBookOrder = bookOrderSelected
        navigateToTicker()
    }

    private val onAvailableExchangeOrderBookClick: (ExchangeOrderBook) -> Unit =
        { bookOrderSelected ->
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentBinding = FragmentBookOrderListBinding.inflate(inflater, container, false)
        fragmentBinding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookOrderAdapter
        }

        if (useCompose) {
            fragmentBinding.list.visibility = View.GONE
            fragmentBinding.composeList.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    ExchangeOrderBookList(
                        availableExchangeOrderBooks = availableBooksViewModel.booksResultForCompose,
                        onClickItem = onAvailableExchangeOrderBookClick,
                    )
                }
            }
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModels()
        initObservers()
        initUIListeners()
    }

    private fun initViewModels() {
        bookOrderViewModel =
            ViewModelProvider(requireActivity()).get(BookOrderViewModel::class.java)
    }

    private fun initObservers() {
        availableBooksViewModel.books.observe(viewLifecycleOwner) {
            bookOrderAdapter.updateBookOrders(it)
            availableBooksViewModel.booksResultForCompose = it

            if (it.isNotEmpty()) {
                showEmptyData(false)
            } else {
                showEmptyData(true)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            refreshData()
        }
    }

    private fun showEmptyData(show: Boolean) {
        fragmentBinding.layoutNoData.root.visibility = if (show) { View.VISIBLE } else { View.GONE }
    }

    private fun initUIListeners() {
        fragmentBinding.apply {
            fabRefresh.setOnClickListener {
                refreshData(forceUpdate = true)
            }
        }
    }

    private fun refreshData(forceUpdate: Boolean = false) {
        if (bookOrderViewModel.internetStatus.isNetworkAvailable()) {
            if (forceUpdate ||
                (
                    availableBooksViewModel.books.value.isNullOrEmpty() &&
                        !availableBooksViewModel.availableBooksUpdatedFromRemote
                    )
            ) {
                log("z0", "Orders books fetch remote")
                if (useRX) {
                    availableBooksViewModel.getAvailableExchangeOrderBooksRX()
                } else {
                    availableBooksViewModel.getAvailableExchangeOrderBooks()
                }
            }
        } else {
            log("z0", "Orders books fetch local")
            availableBooksViewModel.getLocalExchangeOrderBooks()
        }
    }

    private fun navigateToTicker() {
        val navController = findNavController()
        navController.navigate(R.id.tickerFragment)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}
