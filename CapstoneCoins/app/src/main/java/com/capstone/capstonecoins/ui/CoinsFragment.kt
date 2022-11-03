package com.capstone.capstonecoins.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.capstone.capstonecoins.R
import com.capstone.capstonecoins.data.models.availablebooks.BooksDto
import com.capstone.capstonecoins.data.repository.CoinsRepositoryImpl
import com.capstone.capstonecoins.data.retrofit
import com.capstone.capstonecoins.databinding.ActivityMainBinding
import com.capstone.capstonecoins.databinding.FragmentCoinsBinding
import com.capstone.capstonecoins.domain.api.usecases.AvailableBooksUseCase
import com.capstone.capstonecoins.ui.adapters.CoinsAdapter
import com.capstone.capstonecoins.ui.listeners.ListenerAdapter
import com.capstone.capstonecoins.ui.viewmodels.CoinViewmodel
import com.capstone.capstonecoins.ui.viewmodels.ViewModelFactory

class CoinsFragment : Fragment(), ListenerAdapter {
    lateinit var adapter: CoinsAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var navController: NavController
    private var _binding: FragmentCoinsBinding? = null
    private val binding get() = _binding!!


    private val coinViewModel: CoinViewmodel by viewModels {
        ViewModelFactory(AvailableBooksUseCase(CoinsRepositoryImpl(retrofit)))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callServices()
        attachObservers()
    }

    private fun attachObservers() {
        coinViewModel.cryptoBook.observe(this) {
            callDetail(it)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun callDetail(call: BooksDto) {
        adapter = CoinsAdapter(
            call.payload,
            this
        )
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun callServices() {
        coinViewModel.getAvailableBooks()
    }

    override fun listener() {
        Log.d("Mensaje", "CLICK")
        findNavController().navigate(R.id.action_coinsFragment2_to_detailCoinFragment)
    }

}