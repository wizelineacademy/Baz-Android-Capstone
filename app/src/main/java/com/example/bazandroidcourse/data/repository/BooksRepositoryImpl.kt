package com.example.bazandroidcourse.data.repository

import com.example.bazandroidcourse.data.datasource.local.CollectionLocaleDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.GeneralLocalDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.RowByIdLocaleDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.utils.mappers.*
import com.example.bazandroidcourse.data.utils.network.networkManagerUtils

class BooksRepositoryImpl(
    val localeBooksDataSource: GeneralLocalDataSourceInterface<BookEntity>,
    val localeDetailDataSource: RowByIdLocaleDataSourceInterface<BookDetailEntity, String>,
    val localeOrdersDataSource: CollectionLocaleDataSourceInterface<BookOrderEntity, String>,
    val remoteDataSource: CryptoRemoteDataSourceInterface,
) : BooksRepositoryInterface {

    override suspend fun getAllBooks(): List<BookModel> {
        if (networkManagerUtils.isOnline()) {
            val books = remoteDataSource.fetchAllBooks().toDomain()
            localeBooksDataSource.saveAll(books.toBookEntityList())
        }
        return localeBooksDataSource.getAll().toBookListDomain()
    }

    override suspend fun getBookInfo(id: String): BookDetailModel {
        if (networkManagerUtils.isOnline()) {
            val bookDetail = remoteDataSource.fetchBookDetail(id).toDomain()
            localeDetailDataSource.addRow(bookDetail.toEntity())
        }
        val fetch = localeDetailDataSource.getRow(id)
        return fetch.toDomain()
    }

    override suspend fun getBookOrders(id: String): BookOrdersModel {
        if (networkManagerUtils.isOnline()) {
            val bookOrders = remoteDataSource.fetchBookOrders(id).toDomain()
            localeOrdersDataSource.deleteAll(id)
            localeOrdersDataSource.saveAll(bookOrders.toEntityList())
        }
        return localeOrdersDataSource.getAll(id).toOrderDomain()
    }
}