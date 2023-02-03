package com.carteagal.baz_android.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carteagal.baz_android.R
import com.carteagal.baz_android.data.model.availableBook.AvailableBookUI
import com.carteagal.baz_android.data.network.Resources
import com.carteagal.baz_android.data.network.Resources.Success
import com.carteagal.baz_android.databinding.FragmentBookListBinding
import com.carteagal.baz_android.presentation.adapter.BooksAdapter
import com.carteagal.baz_android.presentation.viewmodel.CryptoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookListFragment : Fragment() {

    private lateinit var _binding: FragmentBookListBinding
    private val binding: FragmentBookListBinding get() = _binding

    private lateinit var booksAdapter: BooksAdapter
    private val cryptoViewModel: CryptoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookListBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        setUpRecyclerView()
    }

    private fun loadData(){
        cryptoViewModel.availableBooks.observe(viewLifecycleOwner) { result ->
            when(result){
                is Success -> {
                    booksAdapter.submitList(result.data)
                }
                is Error -> {}
                else ->{}
            }
        }
    }

    private fun setUpRecyclerView(){
        binding.rvBooks.apply {
            booksAdapter = BooksAdapter{ changeFragmentOnClick(it) }
            layoutManager = LinearLayoutManager(requireContext())
            adapter = booksAdapter
        }
    }

    private fun changeFragmentOnClick(bookInfo: AvailableBookUI){
        findNavController().navigate(BookListFragmentDirections.actionBookListFragmentToBookDetailFragment(bookInfo.name, bookInfo.imageUrl))
    }
}