package com.example.baz_android_capstone.data.repository // ktlint-disable package-name

import androidx.room.withTransaction
import com.example.baz_android_capstone.data.db.BookDatabase
import com.example.baz_android_capstone.data.network.BookAPI
import com.example.baz_android_capstone.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: BookAPI,
    private val db: BookDatabase
) {
    private val bookDao = db.bookDao()
    private val orderDao = db.orderDao()
    private val tickerDao = db.tickerDao()

    fun getBooks() = networkBoundResource(
        query = {
            bookDao.getBooks()
        },
        fetch = {
            delay(2000)
            api.getAllAvailableBooks()
        },
        saveFetchResult = {
            db.withTransaction {
                bookDao.deleteBooks()
                bookDao.saveBooks(it)
            }
        }
    )

    fun getOrders(book: String) = networkBoundResource(
        query = {
            orderDao.getOrders()
        },
        fetch = {
            delay(2000)
            api.getOrderBook(book)
        },
        saveFetchResult = {
            db.withTransaction {
                orderDao.deleteOrders()
                orderDao.saveOrders(it)
            }
        }
    )

    fun getTicker(book: String) = networkBoundResource(
        query = {
            tickerDao.getTicker()
        },
        fetch = {
            delay(2000)
            api.getTicker(book)
        },
        saveFetchResult = {
            db.withTransaction {
                tickerDao.deleteTicker()
                tickerDao.saveTicker(it)
            }
        }
    )
}
