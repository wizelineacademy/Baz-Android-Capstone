package com.lefg095.criptoone.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lefg095.criptoone.R
import com.lefg095.criptoone.databinding.FragmentDashboardBinding
import com.lefg095.criptoone.domain.Book
import com.lefg095.criptoone.domain.stateevent.BooksStateEvent
import com.lefg095.criptoone.domain.stateevent.DataState
import com.lefg095.criptoone.ui.adapters.BooksAdapter
import com.lefg095.criptoone.ui.callbacks.ItemBookCallBack
import com.lefg095.criptoone.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(), ItemBookCallBack {
    private lateinit var binding: FragmentDashboardBinding
    private val bookViewModel by activityViewModels<BookViewModel>()
    private var adapterBooks: BooksAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        bookViewModel.bookResponse.observe(viewLifecycleOwner, {
            when(it){
                is DataState.Loading -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    val textoResp = "Elementos: ${it.response.payload.size}"
                    binding.et1.text = textoResp
                    initRecyclerView(it.response.payload)
                }
                is DataState.Error -> {
                    Log.e("_ERROR_", "$it.error.message")
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        bookViewModel.makeApiCall(
            BooksStateEvent.GetBooks
        )
    }

    private fun initRecyclerView(books: List<Book>){
        adapterBooks = BooksAdapter(books, this)
        binding.rvOne.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvOne.adapter = adapterBooks
    }

    override fun showDetailClicket(book: Book) {
        val bookBundle = bundleOf("book" to book)
        view?.findNavController()?.navigate(R.id.detailsFragment, bookBundle)
    }
}