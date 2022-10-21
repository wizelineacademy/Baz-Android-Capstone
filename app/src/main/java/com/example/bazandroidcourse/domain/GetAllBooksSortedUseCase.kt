package com.example.bazandroidcourse.domain

import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllBooksSortedUseCase(
    private val repository: BooksRepositoryInterface,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke() = withContext(defaultDispatcher){
        repository.getAllBooks().sortedBy { it.book }
    }
}