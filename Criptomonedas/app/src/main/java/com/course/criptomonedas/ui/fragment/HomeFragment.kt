package com.course.criptomonedas.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.course.criptomonedas.R
import com.course.criptomonedas.core.service
import com.course.criptomonedas.data.datasource.availablebooks.RemoteDataSourceImpl
import com.course.criptomonedas.data.models.ModelBook
import com.course.criptomonedas.data.repository.AvailableBooksRepositoryImpl
import com.course.criptomonedas.databinding.FragmentHomeBinding
import com.course.criptomonedas.domain.GetAvailableBooksCase
import com.course.criptomonedas.ui.AvailableBooksViewModel
import com.course.criptomonedas.ui.MainViewModelFactory
import com.course.criptomonedas.ui.adapter.AdapterBooks

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    val TAG = "Criptos"
    private lateinit var adapterAvBooks: AdapterBooks
    private lateinit var mRecyclerView: RecyclerView
    private val viewModel: AvailableBooksViewModel by viewModels {
        MainViewModelFactory(
            GetAvailableBooksCase(
                AvailableBooksRepositoryImpl(
                    RemoteDataSourceImpl(
                        service
                    )
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        configAdapter()
        viewModel.getAvailableBooks()

        viewModel.availableBooks.observe(viewLifecycleOwner) {
            adapterAvBooks.submitList(it)
        }
    }

    private fun configAdapter() {
        mRecyclerView = binding.rvListBook
        val listOfBooks: List<ModelBook> = emptyList()
        adapterAvBooks = AdapterBooks {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.book)
            findNavController().navigate(action)
            Log.d(TAG, "configAdapter: ${it.book}")
        }
        mRecyclerView.adapter = adapterAvBooks
        adapterAvBooks.submitList(listOfBooks)
    }
}