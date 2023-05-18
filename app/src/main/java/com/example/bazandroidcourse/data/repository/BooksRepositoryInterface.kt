package com.example.bazandroidcourse.data.repository

import com.example.bazandroidcourse.data.model.BookDetailModel
import com.example.bazandroidcourse.data.model.BookModel
import com.example.bazandroidcourse.data.model.BookOrdersModel

interface BooksRepositoryInterface {
    /***
     * Returns all currencies stored in datasources.
     * @return List<BookModel> returns a list of BookModel entities.
     */
    suspend fun getAllBooks(): List<BookModel>

    /***
     * Returns a detail of a book of a crypto currency
     * @param id:String is the book id example: "btc_mxn"
     * @return BookDetailMode is the detail of currency book
     */
    suspend fun getBookInfo(id: String): BookDetailModel

    /***
     * Returns a object with bids and asks information of a book of a crypto currency
     * @param id:String is the book id example: "btc_mxn"
     * @return BookOrdersModel is an object with bids and asks information
     */
    suspend fun getBookOrders(id: String): BookOrdersModel
}
