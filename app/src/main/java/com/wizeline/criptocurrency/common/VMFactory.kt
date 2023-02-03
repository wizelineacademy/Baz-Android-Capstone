package com.wizeline.criptocurrency.common.adapters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wizeline.criptocurrency.domain.model.use_case.AvailableBooksUseCase
import com.wizeline.criptocurrency.domain.model.use_case.OrderBookUseCase
import com.wizeline.criptocurrency.domain.model.use_case.TickerUseCase
import com.wizeline.criptocurrency.ui.CriptoCurrencyViewModel

class ViewModelFactory(val availableBooksUseCase: AvailableBooksUseCase,
                         val tickerUseCase: TickerUseCase,
                         val orderBookUseCase: OrderBookUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CriptoCurrencyViewModel(availableBooksUseCase,tickerUseCase,orderBookUseCase) as T
}