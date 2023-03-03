package com.example.wizelineandroid.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizelineandroid.R
import com.example.wizelineandroid.RoomApplication
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.core.RetrofitClient
import com.example.wizelineandroid.data.local.entitys.AskEntity
import com.example.wizelineandroid.data.local.entitys.BidsEntity
import com.example.wizelineandroid.data.remote.BooksDataSource
import com.example.wizelineandroid.data.remote.model.*
import com.example.wizelineandroid.databinding.FragmentDetailsBinding
import com.example.wizelineandroid.presentation.order.AskRoomViewModelFactory
import com.example.wizelineandroid.presentation.order.OrderBooksViewModel
import com.example.wizelineandroid.presentation.order.OrderRoomViewModel
import com.example.wizelineandroid.presentation.order.OrderViewModelFactory
import com.example.wizelineandroid.presentation.ticker.TickerBooksViewModel
import com.example.wizelineandroid.presentation.ticker.TickerRoomViewModel
import com.example.wizelineandroid.presentation.ticker.TickerRoomViewModelFactory
import com.example.wizelineandroid.presentation.ticker.TickerViewModelFactory
import com.example.wizelineandroid.repository.order.OrderBookRepoImpl
import com.example.wizelineandroid.repository.order.OrderRoomRepoImpl
import com.example.wizelineandroid.repository.ticker.TickerRepoImpl
import com.example.wizelineandroid.repository.ticker.TickerRoomRepoImpl
import com.example.wizelineandroid.ui.adapter.detail.AskAdapter
import com.example.wizelineandroid.ui.adapter.detail.BidsAdapter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var adapterAvBooks: AskAdapter
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModels<TickerBooksViewModel> {
        TickerViewModelFactory(
            TickerRepoImpl(
                BooksDataSource(RetrofitClient.webService)
            )
        )
    }
    private val viewModelOrder by viewModels<OrderBooksViewModel> {
        OrderViewModelFactory(
            OrderBookRepoImpl(
                BooksDataSource(RetrofitClient.webService)
            )
        )
    }
    private val viewModelRoomTicker: TickerRoomViewModel by activityViewModels {
        TickerRoomViewModelFactory(
            TickerRoomRepoImpl((activity?.application as RoomApplication).database.tickerDao())
        )
    }
    private val viewModelRoomOlder: OrderRoomViewModel by activityViewModels {
        AskRoomViewModelFactory(
            OrderRoomRepoImpl((activity?.application as RoomApplication).database.orderDao())
        )
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        val imgCoin = args.book.split("_")
        binding.nameCoin.text = "${imgCoin[0].uppercase()} - ${imgCoin[1].uppercase()}"
        configAdapter()
        val ahora = System.currentTimeMillis()
        val fecha = Date(ahora)
        val df: DateFormat = SimpleDateFormat("'Ultima actualizacion:' dd 'de' MMMM 'del' yyyy 'a las' HH:mm:ss")
        val salida: String = df.format(fecha)


        context?.let {
            Glide.with(it).load("https://cryptoicons.org/api/icon/${imgCoin[0]}/200")
                .error(R.drawable.ic_baseline_image_24)
                .centerCrop().into(binding.imgCoin).request
        }

        viewModel.fetchTickersBooks(args.book).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    binding.priceLast.text = result.data.payload.last
                    binding.priceHigh.text = result.data.payload.high
                    binding.priceLow.text = result.data.payload.low
                    binding.ultimaActualizacion.text = salida
                    addNewItem(result.data.payload, args.book,salida)

                }
                is Resource.Failure -> {
                    viewModelRoomTicker.allTicker(args.book).observe(viewLifecycleOwner) { ticker ->
                        when (ticker) {
                            is Resource.Success -> {
                                binding.priceLast.text = ticker.data.last
                                binding.priceHigh.text = ticker.data.high
                                binding.priceLow.text = ticker.data.low
                                binding.ultimaActualizacion.text = ticker.data.fecha
                            }
                            else -> {}
                        }
                    }
                }
            }
        }

        viewModelOrder.fetchOrder(args.book).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val entitiesAsk = result.data.payload.asks.map { currency ->
                        AskEntity(
                            id = currency.book,
                            book = currency.book,
                            amount = currency.amount,
                            price = currency.price
                        )
                    }
                    val entitiesBids = result.data.payload.asks.map { currency ->
                        BidsEntity(
                            id = currency.book,
                            book = currency.book,
                            amount = currency.amount,
                            price = currency.price
                        )
                    }
                    adapterAvBooks.submitList(entitiesAsk)
                    binding.rvBids.adapter = BidsAdapter(entitiesBids)

                    addNewItemAsk(result.data.payload.asks)
                    addNewItemBids(result.data.payload.bids)
                }
                is Resource.Failure -> {
                    viewModelRoomOlder.getAsk(args.book).observe(viewLifecycleOwner) { ask ->
                        when (ask) {
                            is Resource.Success -> adapterAvBooks.submitList(ask.data)
                            else -> {}
                        }
                    }

                    viewModelRoomOlder.getBids(args.book).observe(viewLifecycleOwner) { bids ->
                        when (bids) {
                            is Resource.Success -> binding.rvBids.adapter = BidsAdapter(bids.data)
                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun isEntryValid(ticker: GetTicker): Boolean {
        return viewModelRoomTicker.isEntryValid(ticker.toString())
    }

    private fun addNewItem(ticker: GetTicker, id: String,fecha: String) {
        if (isEntryValid(ticker)) viewModelRoomTicker.addNewItem(ticker, id, fecha)
    }
    private fun isEntryValid(list: List<*>): Boolean {
        return viewModelRoomOlder.isEntryValidAsk(list.toString())
    }

    private fun addNewItemAsk(list: List<Ask>) {
        if (isEntryValid(list)) viewModelRoomOlder.addNewItemAsk(list)
    }
    private fun addNewItemBids(list: List<Bids>) {
        if (isEntryValid(list)) viewModelRoomOlder.addNewItemBids(list)
    }

    private fun configAdapter() {
        mRecyclerView = binding.rvAsk
        adapterAvBooks = AskAdapter()
        mRecyclerView.adapter = adapterAvBooks
        adapterAvBooks.submitList(emptyList())
    }
}
