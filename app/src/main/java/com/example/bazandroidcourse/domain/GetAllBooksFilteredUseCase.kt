package com.example.bazandroidcourse.domain

import com.example.bazandroidcourse.data.di.ApplicationScope
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.static.ApplicationCurrencies
import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

class GetAllBooksFilteredUseCase @Inject constructor(
    private val repository: BooksRepositoryInterface,
    @ApplicationScope private val externalScope: CoroutineScope
) {
    /***
     * Returns all valid currencies supported by the application.
     * @param currentCurrency:String is the current currency  to trading operations, example:"mxn"
     * @return List<BookModel> returns a list of BookModel entities.
     */
    suspend operator fun invoke(currentCurrency: String): List<BookModel> =
        withContext(externalScope.coroutineContext) {
            repository.getAllBooks().filter {
                it.book.endsWith("_$currentCurrency") &&
                    ApplicationCurrencies.findByTicker((it.book)) != null
            }
        }
}
