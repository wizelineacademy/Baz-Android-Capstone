package com.example.baz_android_capstone.data.repository // ktlint-disable package-name

import android.util.Log
import com.example.baz_android_capstone.data.models.orderBook.Ask
import com.example.baz_android_capstone.data.models.orderBook.Bid
import com.example.baz_android_capstone.data.models.roomModel.BookDetails
import com.example.baz_android_capstone.data.repository.cache.CacheRepository
import com.example.baz_android_capstone.data.repository.local.BookDatabaseRepository
import com.example.baz_android_capstone.data.repository.remote.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(
    private val bookRepository: BookRepository,
    private val bookDatabaseRepository: BookDatabaseRepository,
    private val cacheRepository: CacheRepository
) : RepositoryInterface {
    private val bookListTest = listOf<BookDetails>()

    override suspend fun getBooks(): List<BookDetails> {
        return getBooksFromDB()
    }

    override suspend fun updateBooks(): List<BookDetails> {
        val newList = getBooksFromAPI()
        bookDatabaseRepository.deleteBooksInDB()
        bookDatabaseRepository.saveBooksToDB(newList)
        cacheRepository.saveBooksToCache(newList)
        return newList
    }

    private suspend fun getBooksFromAPI(): List<BookDetails> {
        val bookList = mutableListOf<BookDetails>()
        bookRepository.getAllBooks().data?.payload?.forEach {
            bookList.add(
                BookDetails(
                    book = it.book,
                    last = bookRepository.getTicker(it.book).data?.payload?.last ?: "",
                    high = bookRepository.getTicker(it.book).data?.payload?.high ?: "",
                    low = bookRepository.getTicker(it.book).data?.payload?.low ?: "",
                    asks = bookRepository.getOrder(it.book).data?.payload?.asks ?: emptyList(),
                    bids = bookRepository.getOrder(it.book).data?.payload?.bids ?: emptyList()
                )
            )
        }
        return bookList
    }

    private suspend fun getBooksFromDB(): List<BookDetails> {
        var bookList = listOf<BookDetails>()
        try {
            CoroutineScope(Dispatchers.IO).launch {
                bookDatabaseRepository.getBooksFromDB().distinctUntilChanged()
                    .collect {
                        if (it.isNullOrEmpty()) {
                            Log.d("TAG", "getBooksFromDB: empty list")
                        } else {
                            bookList = it
                        }
                    }
            }
        } catch (exception: Exception) {
            Log.d("TAG", "getBooksFromDB: ${exception.localizedMessage}")
        }
        if (bookList.size > 0) {
            return bookList
        } else {
            bookList = getBooksFromAPI()
            bookDatabaseRepository.saveBooksToDB(bookList)
        }
        return bookList
    }

    private suspend fun getBooksFromCache(): List<BookDetails> {
        lateinit var bookList: List<BookDetails>
        try {
            bookList = cacheRepository.getBooksFromCache()
        } catch (exception: Exception) {
            Log.d("TAG", "getBooksFromCache: ${exception.localizedMessage}")
        }
        if (bookList.size > 0) {
            return bookList
        } else {
            bookList = getBooksFromDB()
            cacheRepository.saveBooksToCache(bookList)
        }
        return bookListTest
    }
}
