package com.example.bazandroidcourse.data.repository

import com.example.bazandroidcourse.data.datasource.local.CryptoLocalDataSourceInterface
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.utils.network.NetworkManagerInterface

class BooksRepositoryImpl(
    val networkManager: NetworkManagerInterface,
    val localeDataSource: CryptoLocalDataSourceInterface,
    val remoteDataSource: CryptoRemoteDataSourceInterface,
): BooksRepositoryInterface {

    override suspend fun getAllBooks(): List<BookModel> {
        return if(networkManager.isOnline()) {
             remoteDataSource.fetchAllBooks()
        }else{
             localeDataSource.getAllBooks()
        }
    }

    override suspend fun getBookInfo(id: String): BookDetailModel {
       return if (networkManager.isOnline()){
             remoteDataSource.fetchBookDetail(id)
        }else{
             localeDataSource.getBookDetail(id)
        }
    }
}