package com.wizeline.criptocurrency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.room.Room
import com.wizeline.criptocurrency.MainActivity
import com.wizeline.criptocurrency.common.adapters.*
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
    private lateinit var tickerUseCase: TickerUseCase
    private lateinit var orderBookUseCase: OrderBookUseCase
    private lateinit var localDataSource: CryptoCurrencyLocalDataSource
    private val criptoCurrencyVM by activityViewModels<CriptoCurrencyViewModel>(){
        ViewModelFactory(availableBooksUseCase,tickerUseCase,orderBookUseCase)}
    private lateinit var binding: FragmentAvailableBooksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAvailableBooksBinding.inflate(layoutInflater, container, false)
        criptoCurrencyDB= Room.databaseBuilder(
            requireContext(),
            CriptoCurrencyDB::class.java,
            "criptoCurrencyDB"
        ).allowMainThreadQueries()
            .build()
        localDataSource= CryptoCurrencyLocalDataSource(criptoCurrencyDB.getCriptoCurrencyDao())
        availableBooksUseCase = AvailableBooksUseCase(BitsoRepositoryImp(RetrofitClient.repository(),localDataSource,requireContext()))
        tickerUseCase = TickerUseCase(BitsoRepositoryImp(RetrofitClient.repository(),localDataSource,requireContext()))
        orderBookUseCase = OrderBookUseCase(BitsoRepositoryImp(RetrofitClient.repository(),localDataSource,requireContext()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        criptoCurrencyVM.getAvailableBooks()

        binding.apply {
            criptoCurrencyVM.isLoading.observe(viewLifecycleOwner) {
                criptoCurrencyVM.isLoading.observe(viewLifecycleOwner) {
                    if(it){
                        //ShowProgress
                    }else{
                        //HideProgress
                        rvBooks.adapter = AvailableBooksAdapter(criptoCurrencyVM.availableOrderBookList.value ?: emptyList(),
                            goToDetail = { availableBook,coinName->
                                criptoCurrencyVM.setSelectedOrderBook(availableBook?.book.orEmpty())
                                criptoCurrencyVM.setSelectedCoinName(coinName)
                                (activity as MainActivity).loadFragment(OrderBookDetailFragment())
                            }
                        )
                    }
                }

            }

            criptoCurrencyVM.error.observe(viewLifecycleOwner){
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
            }
        }
    }

}