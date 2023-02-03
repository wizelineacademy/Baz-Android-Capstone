package com.jpgl.cryptocurrencies.data



import com.jpgl.cryptocurrencies.data.model.BidsModel
import com.jpgl.cryptocurrencies.data.model.BookModel
import com.jpgl.cryptocurrencies.data.model.TickerModel
import com.jpgl.cryptocurrencies.data.webservice.CryptoService
import com.jpgl.cryptocurrencies.config.RoomModule
import com.jpgl.cryptocurrencies.data.database.entities.BidsEntity
import com.jpgl.cryptocurrencies.data.database.entities.BookEntity
import com.jpgl.cryptocurrencies.data.database.entities.TickerEntity
import com.jpgl.cryptocurrencies.data.model.AsksModel
import com.jpgl.cryptocurrencies.data.database.entities.AsksEntity
import com.jpgl.cryptocurrencies.domain.model.*
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val api : CryptoService,

){

        //AvailableBooks
        suspend fun getAllAvailableBooksFromApi(): List<BooksModelDomain> {
            val response : List<BookModel> = api.getAvailableBooks().bookData
            return response.map { it.toDomain()}
        }


            suspend fun getAllAvailableBooksFromDatabase(): List<BooksModelDomain> {
                val response : List<BookModel> = api.getAvailableBooks().bookData
                return response.map { it.toDomain()}
            }


                suspend fun insertAvailableBooks(books: List<BookEntity>){
                    RoomModule.provideBookDao().insert(books.toTypedArray())
                }

                suspend fun cleanAvailableBooks() {
                    RoomModule.provideBookDao().deleteAllAvailableBooks()
                }

                //Bids
                suspend fun getAllBidsFromApi(book: String): List<BidsModelDomain> {
                    val response : List<BidsModel> = api.getOrderBooks(book).bidsResponse.dataBids
                    return response.map { it.toDomain() }
                }

                suspend fun getAllBidsFromDatabase(): List<BidsModelDomain> {
                    val response : List<BidsEntity> = RoomModule.provideBidsDao().getAllBids()
                    return response.map { it.toDomain()}
                }

                suspend fun insertBids(bids: List<BidsEntity>){
                    RoomModule.provideBidsDao().insert(bids.toTypedArray())
                }

                suspend fun cleanBids() {
                    RoomModule.provideBidsDao().deleteAllBids()
                }
                //Asks
                suspend fun getAllAsksFromApi(book: String): List<AsksModelDomain> {
                    val response : List<AsksModel> = api.getOrderBooks(book).bidsResponse.dataAsks
                    return response.map { it.toDomain() }
                }

                suspend fun getAllAsksFromDatabase(): List<AsksModelDomain> {
                    val response : List<AsksEntity> = RoomModule.provideAsksDao().getAllAsks()
                    return response.map { it.toDomain()}
                }

                suspend fun insertAsks(asks: List<AsksEntity>){
                    RoomModule.provideAsksDao().insert(asks.toTypedArray())
                }

                suspend fun cleanAsks() {
                    RoomModule.provideAsksDao().deleteAllAsks()
                }

                //Ticker
                suspend fun getAllTickerFromApi(book: String): TickerModelDomain {
                    val response : TickerModel = api.getTicker(book).dataTicker
                    return response.toDomain()
                }

            suspend fun getAllTickerFromDatabase(): TickerModelDomain? {
                val response : TickerEntity? = RoomModule.provideTickerDao().getAllTicker()
             return response?.let {
                it.toDomain()
            } ?: null
                }

                suspend fun insertTicker(ticker: TickerEntity){
                    RoomModule.provideTickerDao().insert(ticker)
                }

                suspend fun cleanTicker() {
                    RoomModule.provideTickerDao().deleteAllTicker()
                }


            }