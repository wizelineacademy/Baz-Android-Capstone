package com.wizeline.criptocurrency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wizeline.criptocurrency.R
import com.wizeline.criptocurrency.common.adapters.OpenOrdersListAdapter
import com.wizeline.criptocurrency.common.adapters.utilities.toast
import com.wizeline.criptocurrency.databinding.FragmentBookDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderBookDetailFragment : Fragment() {

    private var book: String = ""
    private var coinName: String = ""
    private val orderBookDetailVM by viewModels<OrderBookDetailViewModel>()
    private lateinit var binding: FragmentBookDetailBinding
    private val orderAsksAdapter: OpenOrdersListAdapter by lazy{
        OpenOrdersListAdapter(emptyList())
    }
    private val orderBidsAdapter: OpenOrdersListAdapter by lazy{
        OpenOrdersListAdapter(emptyList())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailBinding.inflate(layoutInflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        book = arguments?.getString(getString(R.string.book_code)).orEmpty()
        coinName = arguments?.getString(getString(R.string.coin_code)).orEmpty()

        orderBookDetailVM.setSelectedOrderBook(book)
        orderBookDetailVM.setSelectedCoinName(coinName)
        orderBookDetailVM.getTicker(orderBookDetailVM.selectedOrderBook.value.orEmpty())
        binding.apply {
            tvBookName.text = orderBookDetailVM.selectedCoinName.value.orEmpty()

            rvOrderAsks.adapter=orderAsksAdapter
            rvOrderBids.adapter=orderBidsAdapter

            orderBookDetailVM.isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    progressCircular.visibility=View.VISIBLE
                } else {
                    progressCircular.visibility=View.GONE
                }
            }

            orderBookDetailVM.ticker.observe(viewLifecycleOwner) {
                tvBookLastPrice.text = context?.getString(R.string.last, it?.last ?: "")
                tvBookHighPrice.text = context?.getString(R.string.high, it?.high ?: "")
                tvBookLowPrice.text = context?.getString(R.string.low, it?.low ?: "")
            }

            orderBookDetailVM.orderBook.observe(viewLifecycleOwner) {
                orderAsksAdapter.submitList(it?.asks ?: emptyList())
                orderBidsAdapter.submitList(it?.bids ?: emptyList())
            }

            orderBookDetailVM.error.observe(viewLifecycleOwner) {
                toast(it)
            }
        }
    }
}
