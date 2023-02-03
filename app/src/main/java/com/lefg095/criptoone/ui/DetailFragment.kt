package com.lefg095.criptoone.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lefg095.criptoone.databinding.FragmentDetailBinding
import com.lefg095.criptoone.domain.*
import com.lefg095.criptoone.viewmodel.TickerViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import com.lefg095.criptoone.domain.stateevent.DataState
import com.lefg095.criptoone.domain.stateevent.OrderStateEvent
import com.lefg095.criptoone.domain.stateevent.TickerStateEvent
import com.lefg095.criptoone.ui.adapters.AskAdapter
import com.lefg095.criptoone.ui.adapters.BidAdapter
import com.lefg095.criptoone.viewmodel.OrderViewModel
import java.util.*


@AndroidEntryPoint
class DetailFragment: Fragment() {
    lateinit var binding: FragmentDetailBinding
    private val tickerviewModel by activityViewModels<TickerViewModel>()
    private val orderViewModel by activityViewModels<OrderViewModel>()
    private var adapterAsk: AskAdapter? = null
    private var adapterBids: BidAdapter? = null

    var book: Book? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        book = requireArguments().getParcelable<Book>("book")
        subscribeTickerObservers()
        subscribeOrderObservers()
    }

    private fun subscribeOrderObservers() {
        orderViewModel.orderResponse.observe(viewLifecycleOwner, {
            when(it){
                is DataState.Loading -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    printBidsAsks(it.response.payload!!)
                }
                is DataState.Error -> {
                    Log.e("_ERROR_", "$it.error.message")
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        orderViewModel.makeApiCall(
            OrderStateEvent.GetOrder(nameBook = book!!.book)
        )
    }

    private fun subscribeTickerObservers() {
        tickerviewModel.tickerResponse.observe(viewLifecycleOwner, {
            when(it){
                is DataState.Loading -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    printDataScreen(book!!, it.response.payload!!)
                }
                is DataState.Error -> {
                    Log.e("_ERROR_", "$it.error.message")
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        tickerviewModel.makeApiCall(
            TickerStateEvent.GetTicker(nameBook = book!!.book)
        )

    }

    fun printDataScreen(book: Book, ticker: Ticker){
        val numberFormat = NumberFormat.getCurrencyInstance(Locale("es", "US"))

        binding.tvDetail.text = ticker.createdAt
        binding.tvHighPrice.text = numberFormat.format(book.maximum_price.toFloat())
        binding.tvLastPrice.text = numberFormat.format(ticker.last.toFloat())
        binding.tvDownPrice.text = numberFormat.format(book.minimum_price.toFloat())
    }

    private fun printBidsAsks(order: Order) {
        adapterAsk = AskAdapter(order.asks)
        binding.rvAsks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAsks.adapter = adapterAsk


        adapterBids = BidAdapter(order.bids)
        binding.rvBids.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBids.adapter = adapterBids
    }

}