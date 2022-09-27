package com.example.criptobitsoproyectwz.data.Repository


import com.example.criptobitsoproyectwz.data.DataSource.criptoDataSource
import com.example.criptobitsoproyectwz.data.model.Criptos.BaseResult
import retrofit2.Response
import javax.inject.Inject

interface CriptoRepository  {

    /*** Repository
     * clase se encargaría de ir a base de datos(Room) o a internet.
     * mandar info al viewmodel
     */

    suspend fun getAllCriptos(): Response<BaseResult>



 /*   suspend fun getAllCriptos(): BaseResult? {
        return withContext(Dispatchers.IO) {
            val response = Retrofit.getRetrofit().create(BitsoService::class.java)
                .getCriptos("available_books")
            response.body()
        }
    }

    suspend fun getInfoTicker(cripto: String): TicketResult? {
        return withContext(Dispatchers.IO) {
            val response = Retrofit.getRetrofit().create(BitsoService::class.java)
                .getTicketInformation("ticker/?book=$cripto")
            response.body()
        }
    }

    suspend fun getBidsAsk(cripto: String): BaseBookOrder? {
        return withContext(Dispatchers.IO) {
            val response = Retrofit.getRetrofit().create(BitsoService::class.java)
                .getBookOrder("order_book/?book=$cripto")
            response.body()
        }
    }*/

}