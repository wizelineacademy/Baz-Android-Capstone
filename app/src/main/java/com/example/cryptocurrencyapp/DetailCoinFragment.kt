package com.example.cryptocurrencyapp

import android.app.Activity
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cryptocurrencyapp.data.repository.WCCryptoRepositoryImp
import com.example.cryptocurrencyapp.databinding.FragmentDetailCoinBinding
import com.example.cryptocurrencyapp.domain.entity.WCCOrderBookDTO
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.domain.repository.retrofit
import com.example.cryptocurrencyapp.domain.use_case.DetailUseCase
import com.example.cryptocurrencyapp.presentation.view_helper.OrderAdapter
import com.example.cryptocurrencyapp.presentation.view_model.DetailViewModel
import com.example.cryptocurrencyapp.presentation.view_model.ViewModelFactoryTicker


private const val BOOK = "book"

class DetailCoinFragment : Fragment() {
    private lateinit var binding: FragmentDetailCoinBinding
    private lateinit var bidAdapter: OrderAdapter
    private lateinit var askAdapter: OrderAdapter
    private lateinit var book: WCCryptoBookDTO
    private var nameCoin: String? = null
    private var icon: Int = 0

    private val detailModel: DetailViewModel by viewModels {
        ViewModelFactoryTicker(DetailUseCase(WCCryptoRepositoryImp(retrofit)))
    }

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
        detailModel.getOrderBook(book.book)
        detailModel.getTicker(book.book)
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
            if (loading) {
                //binding.crTicker.visibility = View.INVISIBLE
            } else {
                binding.ctLoading.visibility = View.INVISIBLE
                //binding.crTicker.visibility = View.VISIBLE
                // binding.ctLoading.visibility = View.VISIBLE
            }
        }
        detailModel.resumeTicker.observe(requireActivity()) { ticker ->
            binding.imgCoin.setImageResource(book.logo)
            binding.txtCoinName.text = nameCoin
            binding.txtValueMaxPrice.text = ticker.high
            binding.txtValueMinPrice.text = ticker.low
        }
        detailModel.resumeOrder.observe(requireActivity()) { coin ->
            askAdapter.submitList(coin.ask)
            bidAdapter.submitList(coin.bids)
        }
    }

}





