package com.course.criptomonedas.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.course.criptomonedas.R
import com.course.criptomonedas.core.service
import com.course.criptomonedas.data.datasource.availablebooks.RemoteDataSourceImpl
import com.course.criptomonedas.data.models.Ask
import com.course.criptomonedas.data.models.Bid
import com.course.criptomonedas.data.repository.detailsbook.DetailBooksRepositoryImpl
import com.course.criptomonedas.data.repository.orderbook.OrderBookRepositoryImpl
import com.course.criptomonedas.databinding.FragmentDetailBinding
import com.course.criptomonedas.domain.GetDetailBookCase
import com.course.criptomonedas.domain.GetOrderBookCase
import com.course.criptomonedas.ui.DetailBookViewModel
import com.course.criptomonedas.ui.DetailViewModelFactory
import com.course.criptomonedas.ui.adapter.AdapterOrderAsk
import com.course.criptomonedas.ui.adapter.AdapterOrderBids

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var rv_bids: RecyclerView
    private lateinit var rv_asks: RecyclerView
    private lateinit var adapterBids: AdapterOrderBids
    private lateinit var adapterAsks: AdapterOrderAsk

    private val viewModel: DetailBookViewModel by viewModels {
        DetailViewModelFactory(
            GetDetailBookCase(
                DetailBooksRepositoryImpl(
                    RemoteDataSourceImpl(
                        service
                    )
                )
            ), GetOrderBookCase(
                OrderBookRepositoryImpl(
                    RemoteDataSourceImpl(
                        service
                    )
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        configAdapterBids()
        configAdapterAsks()
        val book = args.book
        viewModel.getAvailableBooks(book)
        viewModel.getOrderBooks(book)
        binding.detalle.text = book
        val id = context?.resources?.getIdentifier(
            book,
            "drawable",
            binding.root.context.packageName
        )
        id?.let { binding.imgCoin.setImageResource(it) }

        viewModel.detailBooks.observe(viewLifecycleOwner) {
            binding.ultimoP.text = it.payload.last
            binding.precioAlto.text = it.payload.high
            binding.precioBajo.text = it.payload.low
        }

        viewModel.orderBids.observe(viewLifecycleOwner) {
            adapterBids.submitList(it)
        }

        viewModel.orderAsks.observe(viewLifecycleOwner) {
            adapterAsks.submitList(it)
        }

    }

    private fun configAdapterBids() {
        rv_bids = binding.rvListOrderBook1
        val listOfBooks: List<Bid> = emptyList()
        adapterBids = AdapterOrderBids()
        rv_bids.adapter = adapterBids
        adapterBids.submitList(listOfBooks)
    }

    private fun configAdapterAsks() {
        rv_asks = binding.rvListOrderBook2
        val listOfBooks: List<Ask> = emptyList()
        adapterAsks = AdapterOrderAsk()
        rv_asks.adapter = adapterAsks
        adapterAsks.submitList(listOfBooks)
    }
}