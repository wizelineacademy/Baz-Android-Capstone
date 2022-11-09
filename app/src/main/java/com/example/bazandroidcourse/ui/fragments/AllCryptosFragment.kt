package com.example.bazandroidcourse.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.databinding.FragmentAllCryptosBinding
import com.example.bazandroidcourse.ui.fragments.adapters.BooksAdapter
import com.example.bazandroidcourse.ui.viewmodel.BooksViewModel

class AllCryptosFragment : Fragment() {
    private val viewModel: BooksViewModel = BooksViewModel.createInstance()
    private lateinit var binding: FragmentAllCryptosBinding
    //private var currentCurrency = "mxn"
    private var currentCurrency = "mxn"
    private val adapter = BooksAdapter() {
        navigateTo(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCryptosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllBooks(currentCurrency)
        initVerticalList()
        addObservers()
    }

    fun initGrdList() {
        binding.recyclerBooks.layoutManager = GridLayoutManager(
            requireContext(),
            3
        )
        binding.recyclerBooks.adapter = adapter
    }

    fun initVerticalList() {
        binding.recyclerBooks.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerBooks.adapter = adapter
    }

    fun addObservers() {
        viewModel.allBooks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    fun navigateTo(item: BookModel) {
        val action = AllCryptosFragmentDirections.actionAllCryptosToDetail(item.book)
        findNavController().navigate(action)
    }

}