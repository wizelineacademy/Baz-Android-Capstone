package com.example.bazandroidcourse.data.datasource.remote.api.retrofit

import com.example.bazandroidcourse.data.datasource.remote.api.response.BooksResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.BookOrdersResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.BookDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApplicationAPIInterface {
    /***
     * This endpoint returns a list of existing exchange order books and their respective order placement limits.
     */
    @GET("available_books/")
    suspend fun fetchAvailableBooks():BooksResponse

    /***
     * This endpoint returns trading information from the specified book.
     * @param book:String	Specifies which book to use
     */
    @GET("ticker/")
    suspend fun fetchBookDetail(@Query("book") book: String):BookDetailResponse

    /***
     * This endpoint returns a list of all open orders in the specified book.
     * If the aggregate parameter is set to true, orders will be aggregated by price, and the response will only include
     * the top 50 orders for each side of the book.
     * If the aggregate parameter is set to false, the response will include the full order book.
     * @param book:String	Specifies which book to use
     */
    @GET("order_book/")
    suspend fun fetchBookOrders(@Query("book") book: String):BookOrdersResponse

}