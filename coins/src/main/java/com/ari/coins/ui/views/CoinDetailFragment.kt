package com.ari.coins.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.ari.coins.R
import com.ari.coins.databinding.FragmentCoinDetailBinding
import com.ari.coins.ui.uiModels.ItemString
import com.ari.coins.ui.uiModels.ItemType
import com.ari.coins.ui.uiModels.Result
import com.ari.coins.ui.viewModels.CoinsViewModel
import com.ari.coins.ui.views.adapters.InfoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    companion object {
        const val BOOK_EXTRA = "BOOK_EXTRA"
    }

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding get() = _binding!!

    private val coinsViewModel: CoinsViewModel by activityViewModels()
    private lateinit var infoAdapter: InfoAdapter
    private lateinit var asksAndBidsAdapter: InfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(layoutInflater)
        coinsViewModel.clearCoinDetailView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = arguments?.getString(BOOK_EXTRA)

        book?.let {
            init(it)
        } ?: run {
            Toast.makeText(requireContext(), "Without params", Toast.LENGTH_SHORT).show()
        }
    }

    private fun init(book: String) {
        infoAdapter = InfoAdapter()
        asksAndBidsAdapter = InfoAdapter()
        binding.rvInfo.adapter = infoAdapter
        binding.rvPriceAmount.adapter = asksAndBidsAdapter
        addObservers()
        coinsViewModel.getTicker(book)
        coinsViewModel.getOrderBook(book)
    }

    private fun addObservers() {
        coinsViewModel.ticker.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Error -> Log.e("AVD", "${result.code} - ${result.message}")
                is Result.Success -> {
                    binding.ticker = result.data
                    binding.ivCoin.load(coinsViewModel.getCoinUrlImage(result.data.book)) {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                        placeholder(R.drawable.ic_baseline_image_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                    }
                    val list = listOf(
                        ItemString("last", ItemType.INFO, "Last price", result.data.last),
                        ItemString("high", ItemType.INFO, "High price", result.data.high),
                        ItemString("low", ItemType.INFO, "Low price", result.data.low)
                    )
                    infoAdapter.submitList(list)
                }
            }
        }

        coinsViewModel.orderBook.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Error -> Log.e("AVD", "${result.code} - ${result.message}")
                is Result.Success -> {
                    val list = arrayListOf<ItemString>()
                    list.add(ItemString("-1", ItemType.SECTION, "Asks", ""))
                    list.add(ItemString("-2", ItemType.TITLE, "Price", "Amount"))
                    list.addAll(result.data.asks.mapIndexed { index, ask ->
                        ItemString(
                            "asks$index",
                            ItemType.INFO,
                            ask.price,
                            ask.amount
                        )
                    })
                    list.add(ItemString("-3", ItemType.SECTION, "Bids", ""))
                    list.add(ItemString("-4", ItemType.TITLE, "Price", "Amount"))
                    list.addAll(result.data.bids.mapIndexed { index, bid ->
                        ItemString(
                            "bids$index",
                            ItemType.INFO,
                            bid.price,
                            bid.amount
                        )
                    })
                    asksAndBidsAdapter.submitList(list)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}