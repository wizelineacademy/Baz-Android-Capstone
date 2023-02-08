package com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels

import androidx.lifecycle.ViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.book.response.Book

//View model for activity
class BookOrderViewModel: ViewModel() {

    var isInternetAvailable: Boolean = false

    var selectedBookOrder: Book = Book()

}