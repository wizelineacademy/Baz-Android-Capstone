package com.example.wizelineandroid.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.wizelineandroid.R
import com.example.wizelineandroid.adapter.home.HomeAdapter
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.model.ModelBook
import com.example.wizelineandroid.data.remote.BooksDataSource
import com.example.wizelineandroid.databinding.FragmentHomeBinding
import com.example.wizelineandroid.presentation.BooksViewModelFactory
import com.example.wizelineandroid.presentation.booksViewModel
import com.example.wizelineandroid.repository.RetrofitClient
import com.example.wizelineandroid.repository.available.BooksRepoImpl
import com.example.wizelineandroid.utils.AppConstants


class HomeFragment : Fragment(R.layout.fragment_home), HomeAdapter.onUserClickListener {

    private val viewModel by viewModels<booksViewModel> { BooksViewModelFactory(
        BooksRepoImpl(BooksDataSource(RetrofitClient.webservice))
    ) }
    private lateinit var binding:FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewModel.fetchBooks().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.shimmerViewContainer.startShimmer()

                }
                is Resource.Success -> {
                    binding.shimmerViewContainer.stopShimmer()
                    val filteredCoins = result.data.payload.filter {
                        it.book.contains(AppConstants.TYPE)
                    }
                    binding.rvCoins.adapter = HomeAdapter(filteredCoins,this)
                    binding.shimmerViewContainer.visibility = View.GONE

                }
                is Resource.Failure -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE
                    Log.d("ihvbhviybv", "fetchBooks: yes yes yes3")


                }
            }
        })
    }

    override fun onBookClick(bookss: ModelBook) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
            bookss.book
        )
        findNavController().navigate(action)
    }
}