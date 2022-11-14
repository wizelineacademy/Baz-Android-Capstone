package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.myapplication.R
import com.example.myapplication.adapter.BitsoAdapter
import com.example.myapplication.adapter.BitsoConcatAdapter
import com.example.myapplication.core.Resource
import com.example.myapplication.data.model.Book
import com.example.myapplication.data.remote.BitsoDataSource
import com.example.myapplication.databinding.FragmentBitsoBinding
import com.example.myapplication.presentation.BitsoViewModel
import com.example.myapplication.presentation.BitsoViewModelFactory
import com.example.myapplication.repository.BitsoRepositoryImpl
import com.example.myapplication.repository.RetrofitClient

class BitsoFragment : Fragment(R.layout.fragment_bitso), BitsoAdapter.OnBitsoClickListener {

    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var binding: FragmentBitsoBinding
    private val viewModel by viewModels<BitsoViewModel> {
        BitsoViewModelFactory(
            BitsoRepositoryImpl(
                BitsoDataSource(RetrofitClient.webservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBitsoBinding.bind(view)
        concatAdapter = ConcatAdapter()

        viewModel.fetchAvailableBook().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressVar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressVar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(0, BitsoConcatAdapter(BitsoAdapter(
                            result.data.results,this@BitsoFragment)))
                    }
                    binding.rvBitso.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressVar.visibility = View.GONE
                }
            }
        })
    }

    override fun onBitsoClick(book: Book) {

        val action = BitsoFragmentDirections.actionBitsoFragmentToBitsoDetailsFragment(
            book.book.toString(),
            book.maximumPrice.toString(),
            book.minimumPrice.toString(),
            book.maximumAmount.toString(),
            book.minimumAmount.toString(),

            )
        findNavController().navigate(action)
    }
}

