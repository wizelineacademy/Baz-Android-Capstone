package com.example.bazandroidcourse.domain


import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.static.ApplicationCurrencies
import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllBooksFilteredUseCase @Inject constructor(
     private val repository: BooksRepositoryInterface,
    //@DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(currency: String): List<BookModel> =
        withContext(Dispatchers.IO) {
            repository.getAllBooks().filter {
                it.book.endsWith("_$currency") &&
                        ApplicationCurrencies.findByTicker((it.book)) != null
            }
        }
}