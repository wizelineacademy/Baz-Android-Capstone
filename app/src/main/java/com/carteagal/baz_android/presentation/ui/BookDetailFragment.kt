package com.carteagal.baz_android.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.carteagal.baz_android.R
import com.carteagal.baz_android.data.model.availableBook.AvailableBookUI
import com.carteagal.baz_android.data.model.tickerResponse.TickerResponse
import com.carteagal.baz_android.data.network.Resources
import com.carteagal.baz_android.databinding.FragmentBookDetailBinding
import com.carteagal.baz_android.databinding.ItemPresentationBookBinding
import com.carteagal.baz_android.presentation.viewmodel.CryptoViewModel
import com.carteagal.baz_android.utils.extension.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : Fragment() {

    private lateinit var _binding: FragmentBookDetailBinding
    private val binding: FragmentBookDetailBinding get() = _binding

    private val args: BookDetailFragmentArgs by navArgs<BookDetailFragmentArgs>()
    private val cryptoViewModel: CryptoViewModel by activityViewModels()

    private lateinit var bookName: String
    private lateinit var urlBookImage: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookName = args.bookName
        urlBookImage = args.urlBook
        cryptoViewModel.getTicker(bookName)
        loadData()
    }

    private fun loadData(){
        cryptoViewModel.ticker.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resources.Success -> {
                    setUpView(response.data!!)
                }
                is Resources.Error -> {}
            }
        }
    }

    private fun setUpView(ticker: TickerResponse){
        binding.itemCardInfo.apply {
            txtNameBook.text = ticker.book
            txtPrice.text = ticker.last
            txtHighPrice.text = ticker.high
            txtLowPrice.text = ticker.low
            txtAsk.text = ticker.ask
            txtBind.text = ticker.bid
            txtLastModification.text = ticker.createdAt
            imgLogo.loadImage(urlBookImage)
        }
    }
}