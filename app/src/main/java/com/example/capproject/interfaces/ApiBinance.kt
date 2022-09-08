package com.example.capproject.interfaces


import com.example.capproject.models.book.Books
import com.example.capproject.models.OrderBooks.Broakerbook
import com.example.capproject.models.Tickers.Ticker
import com.example.capproject.models.Tickers.tickets
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiBinance {
//--------
@GET("ticker") //get all
suspend fun getTicker() : Ticker

@GET("ticker/") // get especific
suspend fun specificTicker(@Query("book") book:String ) : tickets
//----------

//------
@GET("available_books")
suspend fun getBooks() : Books

@GET("order_book/")
suspend fun specificBook(@Query("book") book:String ) : Broakerbook
//--------


    companion object {

        private var apiService: ApiBinance? = null

        fun getInstance(): ApiBinance {
            if (apiService == null) {

                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client: OkHttpClient = OkHttpClient.Builder()
                             .addInterceptor(interceptor).build()

                apiService = Retrofit.Builder()
                    .baseUrl("https://api.bitso.com/v3/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiBinance::class.java)
            }
            return apiService!!
        }
    }

}

