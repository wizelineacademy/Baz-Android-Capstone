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
import com.example.cryptocurrencyapp.data.repository.WCCryptoRepositoryImp
import com.example.cryptocurrencyapp.databinding.FragmentCryptoListBinding
import com.example.cryptocurrencyapp.domain.repository.retrofit
import com.example.cryptocurrencyapp.domain.use_case.WCCAvailableUseCase
import com.example.cryptocurrencyapp.presentation.view.adapters.WCCryptoAdapter
import com.example.cryptocurrencyapp.presentation.view_model.AvailableViewModel
import com.example.cryptocurrencyapp.presentation.view_model.ViewModelFactory


class CryptoListFragment : Fragment() {
    private lateinit var binding: FragmentCryptoListBinding
    private lateinit var adapter : WCCryptoAdapter

    private val coinViewModel:AvailableViewModel by viewModels {
        ViewModelFactory(WCCAvailableUseCase(WCCryptoRepositoryImp(retrofit)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = WCCryptoAdapter{ book ->
            findNavController().navigate(R.id.action_cryptoListFragment_to_detailCoinFragment,
                bundleOf("book" to book))
        }
        coinViewModel.getAvailableBook()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptoListBinding.inflate(layoutInflater,container,false)
        binding.rvAvailable.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinViewModel.coins.observe(requireActivity()){ coin ->
            adapter.submitList(coin)
        }

    }
}