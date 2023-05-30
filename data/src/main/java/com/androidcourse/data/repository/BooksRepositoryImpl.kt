package com.androidcourse.data.repository


import com.andcourse.domain.model.BookDetailModel
import com.andcourse.domain.model.BookModel
import com.andcourse.domain.model.BookOrdersModel
import com.andcourse.domain.repository.BooksRepositoryInterface
import com.androidcourse.data.datasource.local.CollectionLocaleDataSourceInterface
import com.androidcourse.data.datasource.local.RXInterface
import com.androidcourse.data.datasource.local.RowByIdLocaleDataSourceInterface
import com.androidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.androidcourse.data.datasource.local.database.room.entities.BookEntity
import com.androidcourse.data.datasource.local.database.room.entities.BookOrderEntity
import com.androidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.androidcourse.data.utils.mappers.*
import com.androidcourse.data.utils.network.NetworkManagerInterface
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
