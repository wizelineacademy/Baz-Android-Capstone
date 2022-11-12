package com.example.bazandroidcourse.data.datasource.remote

import com.example.bazandroidcourse.data.datasource.remote.api.response.BookResponsePayload
import com.example.bazandroidcourse.data.datasource.remote.api.response.DetailResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.OrderPayloadResponse

interface CryptoRemoteDataSourceInterface {
    /***
     * Allows fetch all books of crypto currencies from remote datasource.
     * @return List<BookResponsePayload>
     */
    suspend fun fetchAllBooks():List<BookResponsePayload>
    /***
     * Allows fetch an books of crypto currency from remote datasource.
     * @param book:String is the id of the Currency
     * @return DetailResponse
     */
    suspend fun fetchBookDetail(book:String):DetailResponse
    /***
     * Allows fetch the book of crypto currency orders(bids and asks) from remote datasource.
     * @param book:String is the id of the Currency
     * @return OrderPayloadResponse
     */
    suspend fun fetchBookOrders(book:String):OrderPayloadResponse
}