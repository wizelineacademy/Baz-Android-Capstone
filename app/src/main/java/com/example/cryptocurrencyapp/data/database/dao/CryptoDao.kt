package com.example.cryptocurrencyapp.data.database.dao


import android.util.Log
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

   /* @Query("DELETE FROM available_table")
   suspend fun deleteAllAvailableBookDB()*/

   //Order
   //@Query("SELECT * FROM ")


   //BID
   @Query("SELECT * FROM bid_table WHERE book LIKE :book")
   suspend fun getBidBookDB(book: String): List<BidEntity>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertBidtoDB (bid:List<BidEntity>)


   //ASK
   @Query("SELECT * FROM ask_table WHERE book LIKE :book")
   suspend fun getAskBookDB(book: String): List<AskEntity>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAsktoDB(ask: List<AskEntity>)

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

   /* @Query ("DELETE FROM ask_table WHERE book LIKE :book")
    suspend fun deleteAskList(book: String)

    @Query ("DELETE FROM bid_table WHERE book LIKE :book")
    suspend fun deleteBidList(book: String)*/
}
