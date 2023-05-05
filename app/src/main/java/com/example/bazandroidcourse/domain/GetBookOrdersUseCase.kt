package com.example.bazandroidcourse.domain

import com.example.bazandroidcourse.di.ApplicationScope
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookOrdersUseCase @Inject constructor(
    private val repository: BooksRepositoryInterface,
    @ApplicationScope private val externalScope: CoroutineScope
) {
    /***
     * Returns a object with bids and asks information of a book of a crypto currency
     * @param bookId:String is the book id example: "btc_mxn"
     * @return BookOrdersModel is an object with bids and asks information
     */
    suspend operator fun invoke(bookId: String): BookOrdersModel =
        withContext(externalScope.coroutineContext) {
            repository.getBookOrders(bookId)
        }
}
