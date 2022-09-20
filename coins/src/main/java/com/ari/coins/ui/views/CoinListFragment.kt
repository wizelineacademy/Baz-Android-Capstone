package com.ari.coins.ui.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.ari.coins.R
import com.ari.coins.data.models.AvailableBook
import com.ari.coins.data.models.Result
import com.ari.coins.databinding.FragmentCoinListBinding
import com.ari.coins.ui.viewModels.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment: Fragment() {

    private var _binding: FragmentCoinListBinding? = null
    private val binding: FragmentCoinListBinding get() = _binding!!

    private val coinsViewModel: CoinsViewModel by activityViewModels()
    private lateinit var coinsAdapter: CoinsAdapter

    private val onClickCoin: (AvailableBook) -> Unit = { coin ->
        Toast.makeText(requireContext(), coin.book, Toast.LENGTH_SHORT).show()
        findNavController(this).navigate(R.id.action_coinListFragment_to_coinDetailFragment)
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
        coinsAdapter = CoinsAdapter(onClickCoin)
        binding.rvCoins.adapter = coinsAdapter

        addObservers()
        coinsViewModel.getAvailableBooks()
    }

    private fun addObservers() {
        coinsViewModel.availableBooks.observe(viewLifecycleOwner) { result ->
            when(result) {
                is Result.Error -> {
                    Log.e("AVD", "${result.code} - ${result.message}")
                }
                is Result.Success -> {
                    coinsAdapter.setList(result.data)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}