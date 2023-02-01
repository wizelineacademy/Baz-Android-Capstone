package com.example.wizelineandroid.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.wizelineandroid.R
import com.example.wizelineandroid.adapter.detail.AskAdapter
import com.example.wizelineandroid.adapter.detail.BidsAdapter
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.model.Bids
import com.example.wizelineandroid.data.remote.BooksDataSource
import com.example.wizelineandroid.databinding.FragmentDetailsBinding
import com.example.wizelineandroid.presentation.OrderBooksViewModel
import com.example.wizelineandroid.presentation.OrderViewModelFactory
import com.example.wizelineandroid.presentation.TickerBooksViewModel
import com.example.wizelineandroid.presentation.TickerViewModelFactory
import com.example.wizelineandroid.repository.RetrofitClient
import com.example.wizelineandroid.repository.order.OrderBookRepoImpl
import com.example.wizelineandroid.repository.ticker.TickerRepoImpl

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModels<TickerBooksViewModel> { TickerViewModelFactory(TickerRepoImpl(
        BooksDataSource(RetrofitClient.webservice)
    )) }
    private val viewModelOrder by viewModels<OrderBooksViewModel> { OrderViewModelFactory(OrderBookRepoImpl(
        BooksDataSource(RetrofitClient.webservice)
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        binding.nameCoin.text = args.book

        viewModel.fetchTickersBooks(args.book).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    Log.d("ihvbhviybv", "fetchBooks: yes yes yes1")

                }
                is Resource.Success -> {
                    Log.d("ihvbhviybv", result.data.payload.toString())
                    binding.priceLast.text = result.data.payload.last
                    binding.priceHigh.text = result.data.payload.high
                    binding.priceLow.text = result.data.payload.low

                    when(args.book){
                        "btc_mxn" -> binding.imgCoin.setImageResource(R.drawable.btc_mxn)
                        "bat_mxn" -> binding.imgCoin.setImageResource(R.drawable.bat_mxn)
                        "bch_mxn" -> binding.imgCoin.setImageResource(R.drawable.bch_mxn)
                        "dai_mxn" -> binding.imgCoin.setImageResource(R.drawable.dai_mxn)
                        "eth_mxn" -> binding.imgCoin.setImageResource(R.drawable.eth_mxn)
                        "ltc_mxn" -> binding.imgCoin.setImageResource(R.drawable.ltc_mxn)
                        "mana_mxn" -> binding.imgCoin.setImageResource(R.drawable.mana_mxn)
                        "tusd_mxn" -> binding.imgCoin.setImageResource(R.drawable.tusd_mxn)
                        "usd_mxn" -> binding.imgCoin.setImageResource(R.drawable.usd_mxn)
                        "xrp_mxn" -> binding.imgCoin.setImageResource(R.drawable.xrp_mxn)
                        else -> binding.imgCoin.setImageResource(com.google.android.material.R.drawable.navigation_empty_icon)

                    }
                }
                is Resource.Failure -> {
                    Log.d("ihvbhviybv", "fetchBooks: yes yes yes3")

                }
            }
        })

        viewModelOrder.fetchOrder(args.book).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    Log.d("ihvbhviybv", "fetchBooks: yes yes yes1")

                }
                is Resource.Success -> {
                    binding.rvAsk.adapter = AskAdapter(result.data.payload.asks)
                    binding.rvBids.adapter = BidsAdapter(result.data.payload.bids)
                }
                is Resource.Failure -> {
                    Log.d("ihvbhviybv", "fetchBooks: yes yes yes3")

                }
            }
        })


    }

}