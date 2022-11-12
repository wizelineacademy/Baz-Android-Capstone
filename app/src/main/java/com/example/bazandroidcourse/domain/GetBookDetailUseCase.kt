package com.example.bazandroidcourse.domain

import com.example.bazandroidcourse.data.di.ApplicationScope
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val repository: BooksRepositoryInterface,
    @ApplicationScope private val externalScope: CoroutineScope
) {
    suspend operator fun invoke(id: String): BookDetailModel =
        withContext(externalScope.coroutineContext) {
            repository.getBookInfo(id)
        }
}