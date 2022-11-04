package com.capstone.capstonecoins.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.capstone.capstonecoins.R
import com.capstone.capstonecoins.data.repository.DetailCoinRepositoryImpl
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.repository.models.BookDetail
import com.capstone.capstonecoins.data.retrofit
import com.capstone.capstonecoins.data.utils.BOOKS_KEY
import com.capstone.capstonecoins.data.utils.COIN_KEY
import com.capstone.capstonecoins.databinding.FragmentDetailCoinBinding
import com.capstone.capstonecoins.domain.api.usecases.DetailCoinUseCase
import com.capstone.capstonecoins.ui.viewmodels.DetailCoinViewmodel
import com.capstone.capstonecoins.ui.viewmodels.ViewModelFactorym

class DetailCoinFragment : Fragment() {

    private var _binding: FragmentDetailCoinBinding? = null
    private val binding get() = _binding!!
    var type: String = ""
    var bundle = bundleOf()


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
            var typeCoin = it.getSerializable(BOOKS_KEY)
            type = typeCoin.toString()
            if (typeCoin is Book) {
                callServices(typeCoin.id)
                attachObservers()
                loadListeners()
            }
        }

    }

    private fun loadListeners() {
        with(binding) {

            bundle.putString(COIN_KEY, type)

            btnAsks.setOnClickListener {
                findNavController().navigate(R.id.action_detailCoinFragment_to_bidsFragment, bundle)
            }

            btnBids.setOnClickListener {
                findNavController().navigate(R.id.action_detailCoinFragment_to_bidsFragment, bundle)
            }

        }
    }

    private fun attachObservers() {
        detailCoinViewModel.detailCoin.observe(viewLifecycleOwner) {
            showDetailInfo(it)
        }
    }

    private fun showDetailInfo(detail: BookDetail) = with(binding) {
        tvMaxPrice.text = getString(R.string.max_price, detail.high)
        tvLowerPrice.text = getString(R.string.min_price, detail.low)
        tvLastPrice.text = getString(R.string.last_price, detail.last)
    }

    private fun callServices(typeCoin: String) {
        detailCoinViewModel.getDetailCoin(typeCoin)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}