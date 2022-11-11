package com.course.criptomonedas.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.course.criptomonedas.R
import com.course.criptomonedas.databinding.FragmentHomeBinding
import com.course.criptomonedas.ui.AvailableBooksViewModel
import com.course.criptomonedas.ui.adapter.AdapterBooks
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterAvBooks: AdapterBooks
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var viewModel: AvailableBooksViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        configAdapter()

        viewModel = ViewModelProvider(this)[AvailableBooksViewModel::class.java]

        viewModel.getAvailableBooks()

        viewModel.availableBooks.observe(viewLifecycleOwner) {
            adapterAvBooks.submitList(it)
        }
    }

    private fun configAdapter() {
        mRecyclerView = binding.rvListBook
        adapterAvBooks = AdapterBooks {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.name)
            findNavController().navigate(action)
        }
        mRecyclerView.adapter = adapterAvBooks
        adapterAvBooks.submitList(emptyList())
    }
}