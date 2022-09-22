package com.example.criptobitsoproyectwz.data.Repository


import com.example.criptobitsoproyectwz.data.model.Criptos.BaseResult
import com.example.criptobitsoproyectwz.data.model.OrderBook.BaseBookOrder
import com.example.criptobitsoproyectwz.data.model.Ticket.TicketResult
import com.example.criptobitsoproyectwz.data.network.BitsoService
import com.example.criptobitsoproyectwz.data.network.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryCripto {

    /*** Repository
     * clase se encargaría de ir a base de datos(Room) o a internet.
     * mandar info al viewmodel
     */
    suspend fun getAllCriptos(): BaseResult? {
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
    }

}