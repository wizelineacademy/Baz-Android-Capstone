package com.axiasoft.android.zerocoins.ui.features.available_books.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.databinding.FragmentTickerBinding
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.OpenOrder
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.BookOrderViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.TickerViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.views.adapters.OpenOrdersInBookAdapter
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.ListOrderBookScreenState
import com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states.TickerScreenState

class TickerFragment : Fragment() {

    lateinit var bookOrderViewModel: BookOrderViewModel
    lateinit var tickerViewModel: TickerViewModel

    private var _binding: FragmentTickerBinding? = null
    private val binding get() = _binding!!

    lateinit var asksAdapter: OpenOrdersInBookAdapter
    lateinit var bidsAdapter: OpenOrdersInBookAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tickerViewModel = ViewModelProvider(requireActivity()).get(TickerViewModel::class.java)

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

        tickerViewModel.tickerState.observe(viewLifecycleOwner) {
            when (it) {
                is TickerScreenState.TickerSuccess -> {
                    with(binding){
                        ticker.tvOrderBookName.text = it.ticker.book
                        ticker.tvOrderBookCode.text = it.ticker.book
                        ticker.tvLastPrice.text = it.ticker.last
                        ticker.tvHighPrice.text = it.ticker.high
                        ticker.tvLowerPrice.text = it.ticker.low
                    }
                }
                else -> {}
            }
        }

        tickerViewModel.listOrderBookScreenState.observe(viewLifecycleOwner) {
            when (it) {
                is ListOrderBookScreenState.Success -> {
                    asksAdapter.submitList(it.asks as List<OpenOrder>?)
                    bidsAdapter.submitList(it.bids as List<OpenOrder>?)
                }
                else -> {}
            }
        }

        setupListeners()

        tickerViewModel.selectedBookOrder = bookOrderViewModel.selectedBookOrder

        if (bookOrderViewModel.isInternetAvailable) {
            tickerViewModel.getRemoteTicker()
            tickerViewModel.getRemoteListOrderBook()
        } else {
            tickerViewModel.getLocalTicker()
            tickerViewModel.getLocalListOrderBook()
        }
    }

    private fun setupListeners() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    log("z0", "back fragment")
                    requireActivity().supportFragmentManager.popBackStack()
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        val TAG = "TickerFragment"

        @JvmStatic
        fun newInstance() =
            TickerFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}