package com.capstone.capstonecoins.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.capstonecoins.R
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.repository.models.BookDetail
import com.capstone.capstonecoins.data.utils.BOOKS_KEY
import com.capstone.capstonecoins.databinding.FragmentDetailCoinBinding
import com.capstone.capstonecoins.domain.api.BooksDao
import com.capstone.capstonecoins.ui.adapters.AsksAdapter
import com.capstone.capstonecoins.ui.adapters.BidsAdapter
import com.capstone.capstonecoins.ui.viewmodels.DetailCoinViewmodel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailCoinFragment : Fragment() {
    @Inject
    lateinit var booksDao: BooksDao
    private var _binding: FragmentDetailCoinBinding? = null
    private val binding get() = _binding!!
    var type: String = ""

    private val adapter by lazy {
        BidsAdapter { bid ->
            bid
        }
    }

    private val adapterAsks by lazy {
        AsksAdapter { asks ->
            asks
        }
    }

    private val detailCoinViewModel: DetailCoinViewmodel by viewModels()
    /* {
         ViewModelFactorym(DetailCoinUseCase(DetailCoinRepositoryImpl(retrofit, booksDao)))
     }*/

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
                binding.lvBids.adapter = adapter
                binding.lvAsks.adapter = adapterAsks
            }
        }
    }

    private fun attachObservers() {
        detailCoinViewModel.detailCoin.observe(viewLifecycleOwner) {
            showDetailInfo(it)
        }

        detailCoinViewModel.bidsAsksCoin.observe(viewLifecycleOwner) {
            adapter.submitList(it.payload.bids)
            adapterAsks.submitList(it.payload.asks)
        }
    }


    private fun showDetailInfo(detail: BookDetail) = with(binding) {
        tvMaxPrice.text = getString(R.string.max_price, detail.high)
        tvLowerPrice.text = getString(R.string.min_price, detail.low)
        tvLastPrice.text = getString(R.string.last_price, detail.last)
    }

    private fun callServices(typeCoin: String) {
        detailCoinViewModel.getDetailCoin(typeCoin)
        detailCoinViewModel.getBidsAsksCoin(typeCoin)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}