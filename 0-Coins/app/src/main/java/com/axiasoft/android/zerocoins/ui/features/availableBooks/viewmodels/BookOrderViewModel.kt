package com.axiasoft.android.zerocoins.ui.features.availableBooks.viewmodels

import androidx.lifecycle.ViewModel
import com.axiasoft.android.zerocoins.network.app.InternetConnectionAvailableLiveData
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchangeOrderBook.ExchangeOrderBook

// View model for activity
class BookOrderViewModel : ViewModel() {

    var selectedBookOrder: ExchangeOrderBook = ExchangeOrderBook()

    var internetStatus = InternetConnectionAvailableLiveData()
}
