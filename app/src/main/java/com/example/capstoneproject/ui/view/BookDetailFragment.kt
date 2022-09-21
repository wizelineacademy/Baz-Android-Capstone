package com.example.capstoneproject.ui.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.R
import com.example.capstoneproject.data.model.availableBooks.AvailableBookModel
import com.example.capstoneproject.data.model.ticker.TickerModel
import com.example.capstoneproject.databinding.FragmentBookDetailBinding
import com.example.capstoneproject.ui.adapter.availableBooks.AvailableBooksAdapter
import com.example.capstoneproject.ui.adapter.orderBook.ask.AskAdapter
import com.example.capstoneproject.ui.adapter.orderBook.bids.BidsAdapter
import com.example.capstoneproject.ui.viewmodel.CurrencyViewModel
import com.example.capstoneproject.utils.ResultState

class BookDetailFragment : Fragment() {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!
    private val currencyViewModel: CurrencyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        currencyViewModel.clearDetail()
        setupDetails()
        return binding.root
    }

    private fun setupDetails() {
        val args = arguments
        if (args != null) {
            if (args.containsKey("BOOK")) {
                val book = if (Build.VERSION.SDK_INT >= 33) {
                    args.getParcelable("BOOK", AvailableBookModel::class.java)
                } else {
                    args.getParcelable("BOOK")
                }
                loadDetailData(book)
            }
        }
    }

    private fun loadDetailData(book: AvailableBookModel?) {
        book?.let {
            currencyViewModel.getTicker(book.book)

            currencyViewModel.ticker.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is ResultState.Error -> Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    is ResultState.Success -> {
                        binding.apply {
                            currencyName.text = response.data?.book
                            currencyLastPrice.text = activity?.resources?.getString(R.string.tv_last_price, response.data?.last)
                            currencyHighestPrice.text = activity?.resources?.getString(R.string.tv_highest_price, response.data?.high)
                            currencyLowestPrice.text = activity?.resources?.getString(R.string.tv_lowest_price, response.data?.low)
                        }

                        currencyViewModel.getOrderBook(book.book)

                        setupRecyclerViewsBookOrder(response)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun setupRecyclerViewsBookOrder(response: ResultState<TickerModel>) {
        val askAdapter = AskAdapter()
        val bidAdapter = BidsAdapter()
        val asklayoutManager = LinearLayoutManager(requireContext())
        val bidlayoutManager = LinearLayoutManager(requireContext())

        binding.apply {
            rvAsks.layoutManager = asklayoutManager
            rvBids.layoutManager = bidlayoutManager

            rvAsks.adapter = askAdapter
            rvBids.adapter = bidAdapter
        }

        currencyViewModel.orderBook.observe(viewLifecycleOwner) { responseOrder ->

            binding.apply {
                progressRvAsk.visibility = View.GONE
                progressRvBid.visibility = View.GONE
            }

            when (responseOrder) {
                is ResultState.Error -> Toast.makeText(
                    requireContext(),
                    response.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                is ResultState.Loading -> binding.apply {
                    progressRvAsk.visibility = View.VISIBLE
                    progressRvBid.visibility = View.VISIBLE
                }
                is ResultState.Success -> {
                    askAdapter.submitList(responseOrder.data?.asks)
                    bidAdapter.submitList(responseOrder.data?.bids)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}