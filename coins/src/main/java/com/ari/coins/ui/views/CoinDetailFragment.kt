package com.ari.coins.ui.views

import android.content.DialogInterface
import android.os.Bundle
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
import com.ari.coins.ui.uiModels.DialogData
import com.ari.coins.ui.uiModels.ItemString
import com.ari.coins.ui.uiModels.ItemType
import com.ari.coins.ui.viewModels.CoinsViewModel
import com.ari.coins.ui.views.adapters.InfoAdapter
import com.ari.coins.ui.views.dialogs.GenericBottomSheet
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Ari Valencia
 * @file CoinDetailFragment
 * @description Fragment to display coin detail
 */

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

    private var isDialogShowing = false

    private val book: String? get() = arguments?.getString(BOOK_EXTRA)

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
        coinsViewModel.successTicker.observe(viewLifecycleOwner) { ticker ->
            if (ticker == null) return@observe
            binding.ticker = ticker
            binding.ivCoin.load(coinsViewModel.getCoinUrlImage(ticker.book)) {
                crossfade(true)
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_baseline_image_24)
                error(R.drawable.ic_baseline_broken_image_24)
            }
            val list = listOf(
                ItemString("last", ItemType.INFO, "Last price", ticker.last),
                ItemString("high", ItemType.INFO, "High price", ticker.high),
                ItemString("low", ItemType.INFO, "Low price", ticker.low)
            )
            infoAdapter.submitList(list)
        }

        coinsViewModel.errorTicker.observe(viewLifecycleOwner) { errorTicker ->
            errorTicker?.let { showErrorDialog(it.message) }
        }

        coinsViewModel.errorOrderBook.observe(viewLifecycleOwner) { errorOrderBook ->
            errorOrderBook?.let { showErrorDialog(it.message) }
        }

        coinsViewModel.successOrderBook.observe(viewLifecycleOwner) { orderBook ->
            if (orderBook == null) return@observe
            val list = arrayListOf<ItemString>()
            list.add(ItemString("-1", ItemType.SECTION, getString(R.string.asks), ""))
            list.add(
                ItemString(
                    "-2",
                    ItemType.TITLE,
                    getString(R.string.price),
                    getString(R.string.amount)
                )
            )
            list.addAll(
                orderBook.asks.mapIndexed { index, ask ->
                    ItemString(
                        "asks$index",
                        ItemType.INFO,
                        ask.price,
                        ask.amount
                    )
                }
            )
            list.add(ItemString("-3", ItemType.SECTION, getString(R.string.bids), ""))
            list.add(
                ItemString(
                    "-4",
                    ItemType.TITLE,
                    getString(R.string.price),
                    getString(R.string.amount)
                )
            )
            list.addAll(
                orderBook.bids.mapIndexed { index, bid ->
                    ItemString(
                        "bids$index",
                        ItemType.INFO,
                        bid.price,
                        bid.amount
                    )
                }
            )
            asksAndBidsAdapter.submitList(list)
        }
    }

    private fun showErrorDialog(message: String) {
        if (isDialogShowing) return

        isDialogShowing = true

        val dialogData = DialogData(
            drawableRes = R.drawable.ic_baseline_error_24,
            title = getString(R.string.oops),
            description = message
        )
        val errorDialog = GenericBottomSheet(dialogData, { // On back button clicked
            activity?.onBackPressed()
        }) { // On retry button clicked
            book?.let {
                coinsViewModel.getTicker(it)
                coinsViewModel.getOrderBook(it)
            }
            isDialogShowing = false
        }
        errorDialog.show(childFragmentManager, errorDialog.tag)
        errorDialog.onCancel(object : DialogInterface {
            override fun cancel() {
                isDialogShowing = false
            }

            override fun dismiss() {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
