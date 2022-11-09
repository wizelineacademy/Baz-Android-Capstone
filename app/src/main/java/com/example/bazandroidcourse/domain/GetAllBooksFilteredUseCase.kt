package com.example.bazandroidcourse.domain


import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.static.ApplicationCurrencies
import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllBooksFilteredUseCase(
    private val repository: BooksRepositoryInterface,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(currency: String): List<BookModel> =
        withContext(defaultDispatcher) {
            repository.getAllBooks().filter {
                it.book.endsWith("_$currency") &&
                        ApplicationCurrencies.findByTicker((it.book)) != null
            }
        }
}