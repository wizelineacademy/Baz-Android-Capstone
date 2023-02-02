package com.axiasoft.android.zerocoins.features.coins.views.ui_states

import com.axiasoft.android.zerocoins.features.coins.domain.models.data.book.response.Book

sealed class BooksScreenState() {
    data class BooksSuccess(val data: ArrayList<Book>): BooksScreenState()
    data class BooksErrorOrEmpty(val message: String? = null): BooksScreenState()
    class Loading(): BooksScreenState()
}