package com.javg.cryptocurrencies.data.domain

import com.javg.cryptocurrencies.utils.CRYUtils
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.repository.CRYBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CRYBookUseCase @Inject constructor(
    private val repository: CRYBookRepository){

    suspend operator fun invoke(): MutableList<CRYBook> = withContext(Dispatchers.IO){
        val books = repository.getAvailableBooks()
        val listBookAux = mutableListOf<CRYBook>()

        if (!books.payload.isNullOrEmpty()){
            books.payload?.forEach {
                val imageUrl = "https://cryptoicons.org/api/icon/${CRYUtils.separateStringCoin(it.book)}/200"
                listBookAux.add(CRYBook(it.book,imageUrl))
            }
        }
        listBookAux
    }

}