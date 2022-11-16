package com.wizeline.criptocurrency.common.adapters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wizeline.criptocurrency.domain.model.use_case.AvailableBooksUseCase
import com.wizeline.criptocurrency.domain.model.use_case.OrderBookUseCase
import com.wizeline.criptocurrency.domain.model.use_case.TickerUseCase
import com.wizeline.criptocurrency.ui.AvailableBooksViewModel
import com.wizeline.criptocurrency.ui.OrderBookDetailViewModel

class AvailableBooksViewModelFactory(
    val availableBooksUseCase: AvailableBooksUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        AvailableBooksViewModel(availableBooksUseCase) as T
}

class OrderBooksDetailViewModelFactory(
    val tickerUseCase: TickerUseCase,
    val orderBookUseCase: OrderBookUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        OrderBookDetailViewModel(tickerUseCase, orderBookUseCase) as T
}
