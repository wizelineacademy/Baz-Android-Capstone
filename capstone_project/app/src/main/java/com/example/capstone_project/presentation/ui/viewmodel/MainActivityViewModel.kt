package com.example.capstone_project.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone_project.data.Resource
import com.example.capstone_project.domain.model.AskDomain
import com.example.capstone_project.domain.model.BidDomain
import com.example.capstone_project.domain.model.BookDomain
import com.example.capstone_project.domain.model.TickerDomain
import com.example.capstone_project.domain.usescase.GetAskUseCase
import com.example.capstone_project.domain.usescase.GetAvaibleBookUseCase
import com.example.capstone_project.domain.usescase.GetBidsUseCase
import com.example.capstone_project.domain.usescase.GetTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getavaibleBookUseCase: GetAvaibleBookUseCase,
    private val getBidsUseCase: GetBidsUseCase,
    private val getAskUseCase: GetAskUseCase,
    private val getTickerUseCase: GetTickerUseCase
) : ViewModel() {
    val tickers: MutableStateFlow<Resource<TickerDomain>> = MutableStateFlow<Resource<TickerDomain>>(Resource.loading<TickerDomain>())

    private val _asks = MutableStateFlow<Resource<List<AskDomain>>>(Resource.loading<List<AskDomain>>())
    val asks: StateFlow<Resource<List<AskDomain>>> = _asks

    private val _bids = MutableStateFlow<Resource<List<BidDomain>>>(Resource.loading<List<BidDomain>>())
    val bids: StateFlow<Resource<List<BidDomain>>> = _bids

    private val _avaibleBooks = MutableStateFlow<Resource<List<BookDomain>>>(Resource.loading<List<BookDomain>>())
    val avaibleBooks: StateFlow<Resource<List<BookDomain>>> = _avaibleBooks

    init {
        viewModelScope.launch {
            getavaibleBookUseCase.invoke().collect {
                println(it)
                _avaibleBooks.value = it
            }
        }
    }
}
