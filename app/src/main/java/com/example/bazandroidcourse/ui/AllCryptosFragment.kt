package com.example.bazandroidcourse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.databinding.FragmentAllCryptosBinding
import com.example.bazandroidcourse.ui.adapter.BooksAdapter
import com.example.bazandroidcourse.ui.viewmodel.BooksViewModel

class AllCryptosFragment : Fragment() {
    lateinit var viewModel: BooksViewModel
    private lateinit var binding : FragmentAllCryptosBinding
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
        viewModel =  BooksViewModel(requireContext())
        viewModel.getAllBooks()
        initList()
       addObservers()
    }

    fun initList(){
        binding.recyclerBooks.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerBooks.adapter = adapter
    }

    fun addObservers(){
        viewModel.allBooks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    fun navigateTo(item: BookModel){
        val action = AllCryptosFragmentDirections.actionAllCryptosToDetail(item.book)
        findNavController().navigate(action)
    }

}