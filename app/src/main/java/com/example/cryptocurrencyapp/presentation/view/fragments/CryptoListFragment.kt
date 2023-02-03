package com.example.cryptocurrencyapp.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.FragmentCryptoListBinding
import com.example.cryptocurrencyapp.presentation.view.adapters.WCCryptoAdapter
import com.example.cryptocurrencyapp.presentation.view_model.AvailableViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoListFragment : Fragment() {
    private lateinit var binding: FragmentCryptoListBinding
    private lateinit var adapter : WCCryptoAdapter

    private val coinViewModel by viewModels <AvailableViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = WCCryptoAdapter{ book ->
            findNavController().navigate(R.id.action_cryptoListFragment_to_detailCoinFragment,
                bundleOf("book" to book))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCryptoListBinding.inflate(layoutInflater,container,false)
        binding.rvAvailable.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinViewModel.getAvailableBook()
        coinViewModel.isLoading.observe(requireActivity()) { loading ->
            if (!loading) {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        coinViewModel.coins.observe(requireActivity()){ coin ->
            adapter.submitList(coin)
        }
    }
}