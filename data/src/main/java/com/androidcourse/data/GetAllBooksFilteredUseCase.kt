package com.androidcourse.data


import com.andcourse.domain.model.BookModel
import com.andcourse.domain.model.staticdata.ApplicationCurrency
import com.andcourse.domain.repository.BooksRepositoryInterface
import com.androidcourse.data.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllBooksFilteredUseCase @Inject constructor(
    private val repository: BooksRepositoryInterface,
    @ApplicationScope private val externalScope: CoroutineScope
) {
    /***
     *
     * Returns all the currencies supported by the application, valid for making transactions.
     * @param currentCurrency:String is the current currency  to trading operations, example:"mxn"
     * @return List<BookModel> returns a list of BookModel entities.
     */
    suspend operator fun invoke(currentCurrency: String): List<BookModel> =
        withContext(externalScope.coroutineContext) {
            repository.getAllBooks().filter {
                it.book.endsWith("_$currentCurrency")
                        && ApplicationCurrency.findByTicker((it.book)) != null
            }
        }
}
