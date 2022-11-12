package com.example.bazandroidcourse.domain

import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val repository: BooksRepositoryInterface,
   // private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(id:String): BookDetailModel = withContext(Dispatchers.IO) {
       repository.getBookInfo(id)
    }
}