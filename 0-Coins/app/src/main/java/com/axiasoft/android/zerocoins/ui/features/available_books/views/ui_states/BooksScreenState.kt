package com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.book.response.Book

sealed class BooksScreenState() {
    data class BooksSuccess(val data: ArrayList<Book>): BooksScreenState()
    data class BooksErrorOrEmpty(val message: String? = null): BooksScreenState()
    class Loading(): BooksScreenState()
}