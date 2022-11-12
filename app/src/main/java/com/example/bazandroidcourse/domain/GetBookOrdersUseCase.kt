package com.example.bazandroidcourse.domain

import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookOrdersUseCase @Inject constructor(
    private val repository: BooksRepositoryInterface,
   // @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(bookId:String): BookOrdersModel = withContext(Dispatchers.IO) {
        repository.getBookOrders(bookId)
    }
}