package com.androidcourse.data

import com.andcourse.domain.model.BookOrdersModel
import com.andcourse.domain.repository.BooksRepositoryInterface
import com.androidcourse.data.di.ApplicationScope
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
