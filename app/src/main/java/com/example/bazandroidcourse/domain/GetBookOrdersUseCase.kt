package com.example.bazandroidcourse.domain

import com.example.bazandroidcourse.data.di.ApplicationScope
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookOrdersUseCase @Inject constructor(
    private val repository: BooksRepositoryInterface,
    @ApplicationScope private val externalScope: CoroutineScope
) {
    suspend operator fun invoke(bookId:String): BookOrdersModel =
        withContext(externalScope.coroutineContext) {
            repository.getBookOrders(bookId)
        }
}