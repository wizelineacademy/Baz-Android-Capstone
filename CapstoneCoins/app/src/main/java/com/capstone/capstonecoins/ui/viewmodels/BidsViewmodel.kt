package com.capstone.capstonecoins.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.capstonecoins.data.models.orderbook.OrderBooks
import com.capstone.capstonecoins.data.models.ticker.tickerquery.TickerWithQuery

class BidsViewmodel : ViewModel() {
    val bidsCoin = MutableLiveData<OrderBooks>()
}