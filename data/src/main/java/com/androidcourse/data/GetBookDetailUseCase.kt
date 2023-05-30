package com.androidcourse.data

import com.andcourse.domain.model.BookDetailModel
import com.andcourse.domain.repository.BooksRepositoryInterface
import com.androidcourse.data.di.ApplicationScope
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
