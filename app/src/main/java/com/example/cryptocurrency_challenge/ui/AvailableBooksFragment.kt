package com.example.cryptocurrency_challenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency_challenge.adapter.CurrencyAdapter
import com.example.cryptocurrency_challenge.databinding.FragmentAvailableBooksBinding
import com.example.cryptocurrency_challenge.viewmodel.CryptocurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvailableBooksFragment : Fragment() {

    private val viewModel: CryptocurrencyViewModel by viewModels()

    private var _binding: FragmentAvailableBooksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAvailableBooksBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAvailableBooks()
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.loading.isVisible = it
        })
        viewModel.availableBookModel.observe(viewLifecycleOwner, Observer {
            val adaptador = CurrencyAdapter(it.payLoadList)
            binding.recyclerview.layoutManager= LinearLayoutManager(context)
            binding.recyclerview.adapter= adaptador
        })
    }
}