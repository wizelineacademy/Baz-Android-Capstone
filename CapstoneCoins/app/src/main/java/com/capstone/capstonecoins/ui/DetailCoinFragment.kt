package com.capstone.capstonecoins.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.capstonecoins.R
import com.capstone.capstonecoins.data.models.ticker.tickerquery.TickerWithQuery
import com.capstone.capstonecoins.data.repository.DetailCoinRepositoryImpl
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.retrofit
import com.capstone.capstonecoins.databinding.FragmentDetailCoinBinding
import com.capstone.capstonecoins.domain.api.usecases.DetailCoinUseCase
import com.capstone.capstonecoins.ui.viewmodels.DetailCoinViewmodel
import com.capstone.capstonecoins.ui.viewmodels.ViewModelFactorym

class DetailCoinFragment : Fragment() {

    private var _binding: FragmentDetailCoinBinding? = null
    private val binding get() = _binding!!

    private val detailCoinViewModel: DetailCoinViewmodel by viewModels {
        ViewModelFactorym(DetailCoinUseCase(DetailCoinRepositoryImpl(retrofit)))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCoinBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            var typeCoin = it.getSerializable("Books")
            Log.d("Mensaje", "Args: $typeCoin")
            if (typeCoin is Book) {
                callServices(typeCoin.id)
                attachObservers()
            }
        }

    }

    private fun attachObservers() {
        detailCoinViewModel.detailCoin.observe(viewLifecycleOwner) {
            showDetailInfo(it)
        }
    }

    private fun showDetailInfo(detail: TickerWithQuery) = with(binding) {
        tvMaxPrice.text = buildString {
            append(getString(R.string.max_price))
            append(" ")
            append(detail.payload.high)
        }

        tvLowerPrice.text = buildString {
            append(getString(R.string.min_price))
            append(" ")
            append(detail.payload.low)
        }

        tvLastPrice.text = buildString {
            append(getString(R.string.last_price))
            append(" ")
            append(detail.payload.last)
        }
    }

    private fun callServices(typeCoin: String) {
        detailCoinViewModel.getDetailCoin(typeCoin)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}