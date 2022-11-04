package com.capstone.capstonecoins.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.capstone.capstonecoins.R
import com.capstone.capstonecoins.data.repository.CoinsRepositoryImpl
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.retrofit
import com.capstone.capstonecoins.databinding.FragmentCoinsBinding
import com.capstone.capstonecoins.domain.api.usecases.AvailableBooksUseCase
import com.capstone.capstonecoins.ui.adapters.CoinsAdapter
import com.capstone.capstonecoins.ui.listeners.ListenerAdapter
import com.capstone.capstonecoins.ui.viewmodels.CoinViewmodel
import com.capstone.capstonecoins.ui.viewmodels.ViewModelFactory

class CoinsFragment : Fragment(), ListenerAdapter {
    lateinit var adapter: CoinsAdapter
    lateinit var recyclerView: RecyclerView
    private var _binding: FragmentCoinsBinding? = null
    private val binding get() = _binding!!
    var bundle = bundleOf()
    private val coinViewModel: CoinViewmodel by viewModels {
        ViewModelFactory(AvailableBooksUseCase(CoinsRepositoryImpl(retrofit)))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinsBinding.inflate(inflater, container, false)
        recyclerView = binding.rvCoins
        val view = binding.root
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callServices()
        attachObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun callDetail(call: List<Book>) {
        adapter = CoinsAdapter(
            call,
            this
        )
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun attachObservers() {
        coinViewModel.cryptoBook.observe(this) {
            callDetail(it)
        }
    }

    private fun callServices() {
        coinViewModel.getAvailableBooks()
    }

    override fun listener(book: Book) {
        //  bundle.putSerializable("Books", payload)
        findNavController().navigate(R.id.action_coinsFragment_to_detailCoinFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}