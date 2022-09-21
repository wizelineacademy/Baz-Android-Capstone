package com.example.capstoneproject.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.R
import com.example.capstoneproject.data.model.availableBooks.AvailableBookModel
import com.example.capstoneproject.databinding.FragmentBookListBinding
import com.example.capstoneproject.ui.adapter.availableBooks.AvailableBooksAdapter
import com.example.capstoneproject.ui.viewmodel.CurrencyViewModel
import com.example.capstoneproject.utils.ResultState


class BookListFragment : Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!
    private val currencyViewModel: CurrencyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBookListBinding.inflate(inflater, container, false)

        currencyViewModel.getAvailableBooks()

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        val availableBooksAdapter = AvailableBooksAdapter() { book -> onClickItem(book) }
        val layoutManager = LinearLayoutManager(requireContext())

        binding.apply {
            rvAvailableBooks.layoutManager = layoutManager
            rvAvailableBooks.adapter = availableBooksAdapter
        }

        currencyViewModel.availableBooks.observe(viewLifecycleOwner) { response ->

            binding.progressBarCoins.visibility = View.GONE
            when (response) {
                is ResultState.Error -> Toast.makeText(
                    requireContext(),
                    response.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                is ResultState.Loading -> binding.progressBarCoins.visibility = View.VISIBLE
                is ResultState.Success -> availableBooksAdapter.submitList(response.data)
            }
        }
    }

    private fun onClickItem(book: AvailableBookModel) {
        val bundle = Bundle()
        bundle.putParcelable("BOOK", book)
        NavHostFragment.findNavController(this).navigate(R.id.action_bookListFragment_to_bookDetailFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}