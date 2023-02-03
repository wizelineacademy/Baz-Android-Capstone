package com.example.wizelineandroid.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.wizelineandroid.R
import com.example.wizelineandroid.adapter.detail.AskAdapter
import com.example.wizelineandroid.adapter.detail.BidsAdapter
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.remote.BooksDataSource
import com.example.wizelineandroid.databinding.FragmentDetailsBinding
import com.example.wizelineandroid.presentation.*
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
        var imgCoin = args.book.split("_")

        viewModel.fetchTickersBooks(args.book).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    binding.priceLast.text = result.data.payload.last
                    binding.priceHigh.text = result.data.payload.high
                    binding.priceLow.text = result.data.payload.low
                }
                is Resource.Failure -> {

                }
            }
        })

        viewModelOrder.fetchOrder(args.book).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    binding.rvAsk.adapter = AskAdapter(result.data.payload.asks)
                    binding.rvBids.adapter = BidsAdapter(result.data.payload.bids)
                    context?.let { Glide.with(it).load("https://cryptoicons.org/api/icon/${imgCoin[0]}/200").centerCrop().into(binding.imgCoin) }
                }
                is Resource.Failure -> {
                }
            }
        })
    }

}