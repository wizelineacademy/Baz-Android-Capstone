package com.ari.coins.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.ari.coins.R
import com.ari.coins.databinding.FragmentCoinListBinding
import com.ari.coins.ui.uiModels.AvailableBook
import com.ari.coins.ui.uiModels.Result
import com.ari.coins.ui.viewModels.CoinsViewModel
import com.ari.coins.ui.views.adapters.CoinsAdapter
import com.ari.coins.ui.views.dialogs.GenericBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import com.ari.coins.ui.uiModels.DialogData as DialogData1

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private var _binding: FragmentCoinListBinding? = null
    private val binding: FragmentCoinListBinding get() = _binding!!

    private val coinsViewModel: CoinsViewModel by activityViewModels()
    private lateinit var coinsAdapter: CoinsAdapter

    private val onClickCoin: (AvailableBook) -> Unit = { coin ->
        val bundle = bundleOf(CoinDetailFragment.BOOK_EXTRA to coin.book)
        findNavController(this).navigate(R.id.action_coinListFragment_to_coinDetailFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinsAdapter = CoinsAdapter({ coinsViewModel.getCoinUrlImage(it) }, onClickCoin)
        binding.rvCoins.adapter = coinsAdapter

        addObservers()
    }

    private fun addObservers() {
        coinsViewModel.availableBooks.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Error -> showErrorDialog(result.message)
                is Result.Success -> {
                    coinsAdapter.submitList(result.data)
                }
                is Result.Empty -> {}
            }
        }
    }

    private fun showErrorDialog(message: String) {
        val dialogData = DialogData1(
            drawableRes = R.drawable.ic_baseline_error_24,
            title = getString(R.string.oops),
            description = message
        )
        val errorDialog = GenericBottomSheet(dialogData) { coinsViewModel.getAvailableBooks() }
        errorDialog.show(childFragmentManager, errorDialog.tag + "CoinsListFragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}