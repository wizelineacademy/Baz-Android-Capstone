package com.example.bazandroidcourse.domain

import com.example.bazandroidcourse.data.model.BookDetailModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface
import com.example.bazandroidcourse.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val repository: BooksRepositoryInterface,
    @ApplicationScope private val externalScope: CoroutineScope
) {
    /***
     * Returns a detail of a book of a crypto currency
     * @param bookId:String is the book id example: "btc_mxn"
     * @return BookDetailMode is the detail of currency book
     */
    suspend operator fun invoke(bookId: String): BookDetailModel =
        withContext(externalScope.coroutineContext) {
            repository.getBookInfo(bookId)
        }
}
