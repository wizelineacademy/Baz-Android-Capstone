package com.example.bazandroidcourse.data.repository

import com.example.bazandroidcourse.data.datasource.remote.BookDataSourceInterface
import com.example.bazandroidcourse.data.utils.NetworkManagerInterface

class BooksRepository(
    networkManager:NetworkManagerInterface,
    booksLocaleDataSource: BookDataSourceInterface,
    booksRemoteDataSource: BookDataSourceInterface,
) {


}