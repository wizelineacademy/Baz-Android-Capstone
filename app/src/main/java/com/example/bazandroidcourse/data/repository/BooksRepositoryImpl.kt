package com.example.bazandroidcourse.data.repository

import com.example.bazandroidcourse.data.datasource.local.CollectionLocaleDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.RXInterface
import com.example.bazandroidcourse.data.datasource.local.RowByIdLocaleDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.utils.mappers.*
import com.example.bazandroidcourse.data.utils.network.NetworkManagerInterface
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    val localeBooksDataSource: RXInterface<BookEntity>,
    val localeDetailDataSource: RowByIdLocaleDataSourceInterface<BookDetailEntity, String>,
    val localeOrdersDataSource: CollectionLocaleDataSourceInterface<BookOrderEntity, String>,
    val remoteDataSource: CryptoRemoteDataSourceInterface,
    val networkManager: NetworkManagerInterface
) : BooksRepositoryInterface {

    override suspend fun getAllBooks(): List<BookModel> {
        if (networkManager.isOnline()) {
            remoteDataSource.fetchAllBooks({
                val books = it.toDomain()
                localeBooksDataSource.saveAllRx(books.toBookEntityList())
            })
        }
        return localeBooksDataSource.getAll().toBookListDomain()
    }

    override suspend fun getBookInfo(id: String): BookDetailModel {
        if (networkManager.isOnline()) {
            val bookDetail = remoteDataSource.fetchBookDetail(id).toDomain()
            localeDetailDataSource.addRow(bookDetail.toEntity())
        }
        val fetch = localeDetailDataSource.getRow(id)
        return fetch.toDomain()
    }

    override suspend fun getBookOrders(id: String): BookOrdersModel {
        if (networkManager.isOnline()) {
            val bookOrders = remoteDataSource.fetchBookOrders(id).toDomain()
            localeOrdersDataSource.deleteAll(id)
            localeOrdersDataSource.saveAll(bookOrders.toEntityList())
        }
        return localeOrdersDataSource.getAll(id).toOrderDomain()
    }
}
