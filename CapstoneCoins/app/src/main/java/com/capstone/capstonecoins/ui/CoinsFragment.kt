package com.capstone.capstonecoins.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.capstone.capstonecoins.R
import com.capstone.capstonecoins.data.models.availablebooks.Payload
import com.capstone.capstonecoins.data.repository.CoinsRepositoryImpl
import com.capstone.capstonecoins.data.repository.database.BooksDB
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.retrofit
import com.capstone.capstonecoins.data.utils.BOOKS_KEY
import com.capstone.capstonecoins.databinding.FragmentCoinsBinding
import com.capstone.capstonecoins.domain.api.usecases.AvailableBooksUseCase
import com.capstone.capstonecoins.ui.adapters.CoinsAdapter
import com.capstone.capstonecoins.ui.viewmodels.CoinViewmodel
import com.capstone.capstonecoins.ui.viewmodels.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinsFragment : Fragment() {
    private var _binding: FragmentCoinsBinding? = null
    private val binding get() = _binding!!
    var bundle = bundleOf()

    private val coinViewModel: CoinViewmodel by viewModels {
        ViewModelFactory(AvailableBooksUseCase(CoinsRepositoryImpl(retrofit)))
    }

    private val adapter by lazy {
        CoinsAdapter {
            onBookClick(it)
        }
    }

    private fun onBookClick(book: Book) {
        bundle.putSerializable(BOOKS_KEY, book)
        findNavController().navigate(R.id.action_coinsFragment_to_detailCoinFragment, bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callServices()
        attachObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCoins.adapter = adapter
        callRoom()
    }

    private fun callRoom() {
        val room: BooksDB = Room
            .databaseBuilder(
                requireContext(),
                BooksDB::class.java,
                "BooksDatabase"
            )
            .build()
        CoroutineScope(Dispatchers.IO).launch {
            var lista = ArrayList<Payload>()
            lista.add(
                Payload(
                    0,
                    "Anuma",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja"
                )
            )
            lista.add(
                Payload(
                    1,
                    "David",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja"
                )
            )
            lista.add(
                Payload(
                    2,
                    "Salas",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja",
                    "jajaja"
                )
            )
            room.contactDao().insertAllBooks(lista)
        }
    }

    private fun attachObservers() {
        coinViewModel.cryptoBook.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun callServices() {
        coinViewModel.getAvailableBooks()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}