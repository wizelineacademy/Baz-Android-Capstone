package com.example.wizelineandroid.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wizelineandroid.MainActivity
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.local.entitys.BookEntity
import com.example.wizelineandroid.data.remote.model.ModelBook
import com.example.wizelineandroid.databinding.FragmentHomeBinding
import com.example.wizelineandroid.presentation.books.BookRoomViewModel
import com.example.wizelineandroid.presentation.books.BooksViewModel
import com.example.wizelineandroid.ui.adapter.home.HomeAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(com.example.wizelineandroid.R.layout.fragment_home) {

    private val viewModel: BooksViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var adapterAvBooks: HomeAdapter
    private val viewModelRoom: BookRoomViewModel by viewModels()

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        val mySnackbar = Snackbar.make(view, "No hay conexion a internet", Snackbar.LENGTH_LONG)
        var mainActivity = MainActivity()
        if (context?.let { mainActivity.isInternetAvailable(it) } == false){
            mySnackbar.show()
        }

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
