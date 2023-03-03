package com.example.wizelineandroid.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wizelineandroid.R
import com.example.wizelineandroid.RoomApplication
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.core.RetrofitClient
import com.example.wizelineandroid.data.local.entitys.BookEntity
import com.example.wizelineandroid.data.remote.BooksDataSource
import com.example.wizelineandroid.data.remote.model.ModelBook
import com.example.wizelineandroid.databinding.FragmentHomeBinding
import com.example.wizelineandroid.presentation.books.BookRoomViewModel
import com.example.wizelineandroid.presentation.books.BookRoomViewModelFactory
import com.example.wizelineandroid.presentation.books.BooksViewModel
import com.example.wizelineandroid.presentation.books.BooksViewModelFactory
import com.example.wizelineandroid.repository.available.BookRoomRepoImpl
import com.example.wizelineandroid.repository.available.BooksRepoImpl
import com.example.wizelineandroid.ui.adapter.home.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel by viewModels<BooksViewModel> {
        BooksViewModelFactory(
            BooksRepoImpl(
                BooksDataSource(RetrofitClient.webService)
            )
        )
    }
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var adapterAvBooks: HomeAdapter
    private val viewModelRoom: BookRoomViewModel by activityViewModels {
        BookRoomViewModelFactory(
            BookRoomRepoImpl((activity?.application as RoomApplication).database.bookDao())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        configAdapter()

        viewModel.fetchBooks().observe(
            viewLifecycleOwner,
            Observer { result ->
                when (result) {
                    is Resource.Loading -> binding.shimmerViewContainer.startShimmer()

                    is Resource.Success -> {
                        binding.shimmerViewContainer.stopShimmer()
                        val entities = result.data.payload.map { currency ->
                            BookEntity(
                                itemName = currency.book,
                                id = currency.book,
                                maximum_price = currency.maximum_price,
                                minimum_price = currency.minimum_price
                            )
                        }
                        adapterAvBooks.submitList(entities)
                        binding.shimmerViewContainer.visibility = View.GONE
                        addNewItem(result.data.payload)
                    }
                    is Resource.Failure -> {
                        binding.shimmerViewContainer.stopShimmer()
                        binding.shimmerViewContainer.visibility = View.GONE
                        viewModelRoom.getBooks().observe(
                            viewLifecycleOwner,
                            Observer { room ->
                                when (room) {
                                    is Resource.Success -> adapterAvBooks.submitList(room.data)
                                    else -> {}
                                }
                            }
                        )
                    }
                }
            }
        )
    }

    private fun isEntryValid(list: List<ModelBook>): Boolean {
        return viewModelRoom.isEntryValid(list.toString())
    }

    private fun addNewItem(list: List<ModelBook>) {
        if (isEntryValid(list)) viewModelRoom.addNewItem(list)
    }
    private fun configAdapter() {
        mRecyclerView = binding.rvCoins
        adapterAvBooks = HomeAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.itemName)
            findNavController().navigate(action)
        }
        mRecyclerView.adapter = adapterAvBooks
        adapterAvBooks.submitList(emptyList())
    }
}
