package com.example.cryptocurrencyapp.presentation.view.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.cryptocurrencyapp.databinding.FragmentDetailCoinBinding
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.presentation.view.adapters.OrderAdapter
import com.example.cryptocurrencyapp.presentation.view_model.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val BOOK = "book"

@AndroidEntryPoint
class DetailCoinFragment : Fragment() {
    private lateinit var binding: FragmentDetailCoinBinding
    private lateinit var bidAdapter: OrderAdapter
    private lateinit var askAdapter: OrderAdapter
    private lateinit var book: WCCryptoBookDTO
    private var nameCoin: String? = null
    private var icon: Int = 0

    private val detailModel by viewModels<DetailViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            book = if (Build.VERSION.SDK_INT >= 33) {
                it.getParcelable(BOOK, WCCryptoBookDTO::class.java) ?: WCCryptoBookDTO()
            } else {
                it.getParcelable(BOOK) ?: WCCryptoBookDTO()
            }
        }
        bidAdapter = OrderAdapter()
        askAdapter = OrderAdapter()
        detailModel.getTicker(book.book)
        detailModel.getOrderBook(book.book)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailCoinBinding.inflate(layoutInflater, container, false)
        binding.rvAskDetail.adapter = askAdapter
        binding.rvBidDetail.adapter = bidAdapter


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailModel.isLoading.observe(requireActivity()) { loading ->
            if (!loading) {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
        detailModel.resumeTicker.observe(requireActivity()) { ticker ->
            binding.imgCoin.setImageResource(book.logo)
            binding.txtCoinName.text = book.name
            binding.txtValueMaxPrice.text = ticker.high
            binding.txtValueMinPrice.text = ticker.low
        }
        detailModel.resumeOrder.observe(requireActivity()) { coin ->
            askAdapter.submitList(coin.ask)
            bidAdapter.submitList(coin.bids)
        }
    }

}





