package com.example.wizelineandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.wizelineandroid.R
import com.example.wizelineandroid.ui.adapter.home.HomeAdapter
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.core.RetrofitClient
import com.example.wizelineandroid.data.local.BookEntity
import com.example.wizelineandroid.data.remote.model.ModelBook
import com.example.wizelineandroid.data.remote.BooksDataSource
import com.example.wizelineandroid.databinding.FragmentHomeBinding
import com.example.wizelineandroid.presentation.BookRoomViewModel
import com.example.wizelineandroid.presentation.BookRoomViewModelFactory
import com.example.wizelineandroid.presentation.BooksViewModelFactory
import com.example.wizelineandroid.presentation.booksViewModel
import com.example.wizelineandroid.repository.RoomApplication
import com.example.wizelineandroid.repository.available.BooksRepoImpl

class HomeFragment: Fragment(R.layout.fragment_home), HomeAdapter.OnUserClickListener {
    private val viewModel by viewModels<booksViewModel> { BooksViewModelFactory(
        BooksRepoImpl(BooksDataSource(RetrofitClient.webService))
    ) }
    private lateinit var binding:FragmentHomeBinding
    private val viewModelRoom: BookRoomViewModel by activityViewModels {
        BookRoomViewModelFactory(
            (activity?.application as RoomApplication).database
                .itemDao()
        )
    }

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
                    val entities = result.data.payload.map { currency ->
                        BookEntity(
                            itemName = currency.book,
                            id = currency.book,
                            maximum_price = currency.maximum_price,
                            minimum_price = currency.minimum_price
                        )
                    }
                    binding.rvCoins.adapter = HomeAdapter(entities,this)
                    binding.shimmerViewContainer.visibility = View.GONE
                    addNewItem(result.data.payload)


                }
                is Resource.Failure -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE
                    viewModelRoom.allBooks.observe(this.viewLifecycleOwner) { items ->
                        binding.rvCoins.adapter = HomeAdapter(items,this)
                    }

                }
            }
        })
    }

    private fun isEntryValid(list: List<ModelBook>): Boolean {
        return viewModelRoom.isEntryValid(
           list.toString()
        )
    }

    private fun addNewItem(list: List<ModelBook>) {
        if (isEntryValid(list)) {
            viewModelRoom.addNewItem(
                list
            )

        }
    }

    override fun onBookClick(book: BookEntity) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
            book.itemName
        )
        findNavController().navigate(action)    }
}