package com.example.cryptocurrencyapp.data.database.dao


import androidx.room.*
import com.example.cryptocurrencyapp.data.database.entities.*
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO

@Dao
interface CryptoDao {
    //Available
    @Query("SELECT * FROM available_table")
    suspend fun getAllAvailableBookDB(): List<AvailableBookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAvailableBooDB (book: List<AvailableBookEntity>)

    @Update
   suspend fun updateAvailableBookDB(bookList: List<AvailableBookEntity>)

   //TICKER
   @Query("SELECT * FROM ticker_table WHERE book LIKE :book")
   suspend fun getickerBD(book: String): TickerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickerBD (ticker: TickerEntity)


   //BID
   @Query("SELECT * FROM bid_table WHERE book LIKE :book")
   suspend fun getBidBookDB(book: String): List<BidEntity>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertBidtoDB (bid: List<BidEntity>)

    @Query("DELETE FROM bid_table WHERE book LIKE :book")
    fun deleteBidsListDB(book: String)


    //ASK
   @Query("SELECT * FROM ask_table WHERE book LIKE :book")
   suspend fun getAskBookDB(book: String): List<AskEntity>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAsktoDB(ask: List<AskEntity>)

    @Query("DELETE FROM ask_table WHERE book LIKE :book")
    fun deleteAsksListDB(book: String)



    @Transaction
   suspend fun getOrderBookDB(book: String): WCCOrdeRDTO{
       val ask = getAskBookDB(book)
       val bid = getBidBookDB(book)

       return WCCOrdeRDTO(
           ask = ask.map { it.toWCCOrderBookDTO() },
           bids = bid.map { it.toWCCOrderBookDTO() }
       )
   }

    @Transaction
    suspend fun insertOrderBookFromDatabase(askList: List<AskEntity>, bidList: List<BidEntity>){
       insertAsktoDB(askList)
        insertBidtoDB(bidList)
    }

    @Transaction
   suspend fun deleteOrderBookDB(book: String){
        deleteBidsListDB(book)
        deleteAsksListDB(book)
    }
}
