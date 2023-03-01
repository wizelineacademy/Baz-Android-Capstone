package com.axiasoft.android.zerocoins.ui.features.available_books.views.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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
import com.axiasoft.android.zerocoins.databinding.FragmentTickerBinding
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.OpenOrder
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.BookOrderViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.TickerViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.views.adapters.OpenOrdersInBookAdapter
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.ListOrderBookScreenState
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.TickerScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TickerFragment : Fragment() {

    lateinit var bookOrderViewModel: BookOrderViewModel

    private val tickerViewModel by viewModels<TickerViewModel>()
    private var _binding: FragmentTickerBinding? = null
    private val binding get() = _binding!!

    lateinit var asksAdapter: OpenOrdersInBookAdapter
    lateinit var bidsAdapter: OpenOrdersInBookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bookOrderViewModel =
            ViewModelProvider(requireActivity()).get(BookOrderViewModel::class.java)

        asksAdapter = OpenOrdersInBookAdapter()
        bidsAdapter = OpenOrdersInBookAdapter()

        with(binding) {
            rvAsks.layoutManager = LinearLayoutManager(requireContext())
            rvBids.layoutManager = LinearLayoutManager(requireContext())
            rvAsks.adapter = asksAdapter
            rvBids.adapter = bidsAdapter
        }

        tickerViewModel.selectedBookOrder = bookOrderViewModel.selectedBookOrder

        tickerViewModel.tickerState.observe(viewLifecycleOwner) {
            when (it) {
                is TickerScreenState.TickerSuccess -> {
                    with(binding) {
                        ticker.tvOrderBookName.text = it.ticker.book
                        ticker.tvOrderBookCode.text = it.ticker.book
                        ticker.tvLastPrice.text = it.ticker.last
                        ticker.tvHighPrice.text = it.ticker.high
                        ticker.tvLowerPrice.text = it.ticker.low
                    }
                }
                else -> {
                    showErrorDialog()
                }
            }
        }

        tickerViewModel.listOrderBookScreenState.observe(viewLifecycleOwner) {
            when (it) {
                is ListOrderBookScreenState.Success -> {
                    asksAdapter.submitList(it.asks as List<OpenOrder>?)
                    bidsAdapter.submitList(it.bids as List<OpenOrder>?)
                }
                else -> {
                    clearData()
                }
            }
        }
        initObservers()
        setupListeners()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                refreshData()
            }
        }
    }

    private fun refreshData() {
        if (bookOrderViewModel.internetStatus.isNetworkAvailable()) {
            log("z0", "Ticker & orders fetching remote")
            tickerViewModel.getRemoteTicker()
            tickerViewModel.getRemoteListOrderBook()
        } else {
            log("z0", "Ticker & orders fetching local")
            tickerViewModel.getLocalTicker()
            tickerViewModel.getLocalListOrderBook()
        }
    }

    private fun setupListeners() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                    clearData()
                }
            }
        )
    }

    private fun clearData() {
        asksAdapter.submitList(emptyList())
        bidsAdapter.submitList(emptyList())
    }

    private fun showErrorDialog() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage(getString(R.string.ticker_error_fetching_data))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.ticker_error_fetching_data_exit)) { _, _ ->
                findNavController().popBackStack()
            }
        val alert = dialogBuilder.create()
        alert.setTitle("AlertDialogExample")
        alert.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
