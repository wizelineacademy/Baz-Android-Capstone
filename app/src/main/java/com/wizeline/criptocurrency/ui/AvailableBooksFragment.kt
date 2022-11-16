package com.wizeline.criptocurrency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.wizeline.criptocurrency.R
import com.wizeline.criptocurrency.common.adapters.*
import com.wizeline.criptocurrency.common.adapters.utilities.toast
import com.wizeline.criptocurrency.config.InitApplication.Companion.criptoCurrencyDB
import com.wizeline.criptocurrency.data.database.CriptoCurrencyDB
import com.wizeline.criptocurrency.data.database.data_source.CryptoCurrencyLocalDataSource
import com.wizeline.criptocurrency.data.repository.BitsoRepositoryImp
import com.wizeline.criptocurrency.databinding.FragmentAvailableBooksBinding
import com.wizeline.criptocurrency.domain.model.use_case.AvailableBooksUseCase

class AvailableBooksFragment : Fragment() {

    private lateinit var availableBooksUseCase: AvailableBooksUseCase
    private lateinit var localDataSource: CryptoCurrencyLocalDataSource
    private val availableBooksVM by activityViewModels<AvailableBooksViewModel>() {
        AvailableBooksViewModelFactory(availableBooksUseCase)
    }
    private lateinit var binding: FragmentAvailableBooksBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAvailableBooksBinding.inflate(
            layoutInflater,
            container, false
        )
        criptoCurrencyDB = Room.databaseBuilder(
            requireContext(),
            CriptoCurrencyDB::class.java,
            "criptoCurrencyDB"
        ).allowMainThreadQueries()
            .build()
        localDataSource = CryptoCurrencyLocalDataSource(criptoCurrencyDB.getCriptoCurrencyDao())
        availableBooksUseCase = AvailableBooksUseCase(
            BitsoRepositoryImp(RetrofitClient.repository(), localDataSource, requireContext())
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        availableBooksVM.getAvailableBooks()

        binding.apply {
            availableBooksVM.isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    progressCircular.visibility=View.VISIBLE
                } else {
                    progressCircular.visibility=View.GONE
                }
            }
            availableBooksVM.availableOrderBookList.observe(viewLifecycleOwner) {

                rvBooks.adapter = AvailableBooksAdapter(
                    it ?: emptyList(),
                    goToDetail = { availableBook, coinName ->
                        val bundle = bundleOf(
                            "book_code" to availableBook?.book.orEmpty(),
                            "coin_code" to coinName
                        )
                        findNavController().navigate(R.id.orderBookDetailFragment, bundle)

                    }
                )
            }

            availableBooksVM.error.observe(viewLifecycleOwner) {
                toast(it)
            }
        }
    }
}
