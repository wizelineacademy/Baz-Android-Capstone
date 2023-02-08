package com.javg.cryptocurrencies.data.domain

import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.repository.CRYBookRepository
import com.javg.cryptocurrencies.ext.separateStringCoins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CRYBookUseCase @Inject constructor(
    private val repository: CRYBookRepository){

    suspend operator fun invoke(): List<CRYBook> = withContext(Dispatchers.IO){
        val books = repository.getAvailableBooks()

        if (books.isEmpty())
            mutableListOf<CRYBook>()
        else {
            books.map { it.imageUrl = "https://cryptoicons.org/api/icon/${it.book.separateStringCoins()}/200" }
        }
        books
    }

}