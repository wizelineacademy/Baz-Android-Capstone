package com.example.bazandroidcourse.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazandroidcourse.data.datasource.local.CryptoLocalDataSourceImpl
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceImpl
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.apiInstance
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryImpl
import com.example.bazandroidcourse.databinding.FragmentAllCryptosBinding
import com.example.bazandroidcourse.domain.GetAllBooksFilteredUseCase
import com.example.bazandroidcourse.domain.GetBookDetailUseCase
import com.example.bazandroidcourse.domain.GetBookOrdersUseCase
import com.example.bazandroidcourse.ui.fragments.adapters.BooksAdapter
import com.example.bazandroidcourse.ui.viewmodel.BooksViewModel

class AllCryptosFragment : Fragment() {
    private val repository = BooksRepositoryImpl(
        CryptoLocalDataSourceImpl(),
        CryptoRemoteDataSourceImpl(apiInstance)
    )
    private val getBooksUseCase = GetAllBooksFilteredUseCase(
        repository
    )
    private val getBookDetailUseCase = GetBookDetailUseCase(
        repository
    )

    private val getBookOrdersUseCase = GetBookOrdersUseCase(
        repository
    )
    private val viewModel: BooksViewModel = BooksViewModel(
        getBooksUseCase,
        getBookDetailUseCase,
        getBookOrdersUseCase
    )
    private lateinit var binding : FragmentAllCryptosBinding
    private var currentCurrency = "mxn"
    private val adapter = BooksAdapter(){
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
        initList()
       addObservers()
    }

    fun initList() {
        binding.recyclerBooks.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerBooks.adapter = adapter
    }

    fun addObservers() {
        viewModel.allBooks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    fun navigateTo(item: BookModel) {
        val action = AllCryptosFragmentDirections.actionAllCryptosToDetail(item.book)
        findNavController().navigate(action)
    }

}