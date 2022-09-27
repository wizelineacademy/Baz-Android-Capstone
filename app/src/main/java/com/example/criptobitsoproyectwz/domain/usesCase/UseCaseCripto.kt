package com.example.criptobitsoproyectwz.data.Repository

import com.example.criptobitsoproyectwz.data.model.Criptos.BaseResult
import com.example.criptobitsoproyectwz.data.model.OrderBook.BaseBookOrder
import com.example.criptobitsoproyectwz.data.model.Ticket.TicketResult
import retrofit2.Response
import javax.inject.Inject

class useCaseCripto @Inject constructor(private val repository: CriptoRepository) {

    /***
     * Dominio o interactors es la lógica de la negocio. Una única acción
     * Casos de uso, interactors
     * Si quieres pasar a la ui datos tendremos un caso de uso para cada uno y este llama al repositorio
     */

    //invoke se llama automaticamente al declarar una instancia, debe retonar lo que dice la clase
    suspend operator fun invoke(): Response<BaseResult> = repository.getAllCriptos()

  /*  suspend fun useCaseInfoCripto(crip: String): TicketResult? = CriptoRepository().getInfoTicker(crip)


    suspend fun useCaseAskBids(crip: String): BaseBookOrder? = CriptoRepository().getBidsAsk(crip)*/


}