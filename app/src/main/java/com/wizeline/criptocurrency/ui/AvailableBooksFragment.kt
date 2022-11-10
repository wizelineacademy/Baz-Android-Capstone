package com.wizeline.criptocurrency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.room.Room
import com.wizeline.criptocurrency.MainActivity
import com.wizeline.criptocurrency.common.adapters.*
import com.wizeline.criptocurrency.common.adapters.utilities.toast
import com.wizeline.criptocurrency.config.InitApplication.Companion.criptoCurrencyDB
import com.wizeline.criptocurrency.data.database.CriptoCurrencyDB
import com.wizeline.criptocurrency.data.database.data_source.CryptoCurrencyLocalDataSource
import com.wizeline.criptocurrency.data.repository.BitsoRepositoryImp
import com.wizeline.criptocurrency.databinding.FragmentAvailableBooksBinding
import com.wizeline.criptocurrency.domain.model.use_case.AvailableBooksUseCase
import com.wizeline.criptocurrency.domain.model.use_case.OrderBookUseCase
import com.wizeline.criptocurrency.domain.model.use_case.TickerUseCase

class AvailableBooksFragment : Fragment() {

    private lateinit var availableBooksUseCase: AvailableBooksUseCase
    private lateinit var localDataSource: CryptoCurrencyLocalDataSource
    private val availableBooksVM by activityViewModels<AvailableBooksViewModel>(){
        AvailableBooksViewModelFactory(availableBooksUseCase)}
    private lateinit var binding: FragmentAvailableBooksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAvailableBooksBinding.inflate(layoutInflater,
            container, false)
        criptoCurrencyDB= Room.databaseBuilder(
            requireContext(),
            CriptoCurrencyDB::class.java,
            "criptoCurrencyDB"
        ).allowMainThreadQueries()
            .build()
        localDataSource= CryptoCurrencyLocalDataSource(criptoCurrencyDB.getCriptoCurrencyDao())
        availableBooksUseCase = AvailableBooksUseCase(
            BitsoRepositoryImp(RetrofitClient.repository(),localDataSource,requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        availableBooksVM.getAvailableBooks()

        binding.apply {
            availableBooksVM.isLoading.observe(viewLifecycleOwner) {
                if(it){
                    toast("Loading...")
                    //ShowProgress
                }else{
                    //HideProgress
                }
            }
                availableBooksVM.availableOrderBookList.observe(viewLifecycleOwner) {

                        rvBooks.adapter = AvailableBooksAdapter(it ?: emptyList(),
                            goToDetail = { availableBook,coinName->
                                (activity as MainActivity).loadFragment(
                                    OrderBookDetailFragment.newInstance(
                                        bookParameter = availableBook?.book.orEmpty(),
                                        coinNameParameter = coinName))
                            }
                        )

                }


            availableBooksVM.error.observe(viewLifecycleOwner){
                toast(it)
            }
        }
    }

}