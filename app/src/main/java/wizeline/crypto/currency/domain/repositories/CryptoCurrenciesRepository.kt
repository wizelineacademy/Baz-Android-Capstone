package wizeline.crypto.currency.domain.repositories

import wizeline.crypto.currency.data.models.AvailableBooksDto
import wizeline.crypto.currency.domain.model.AvailableBooksModel
import wizeline.crypto.currency.domain.model.OrderbookModel
import wizeline.crypto.currency.domain.model.TradingInformationModel

interface CryptoCurrenciesRepository {

    suspend fun getAvailableBooks(): List<AvailableBooksModel>

    suspend fun getInformationTrading(book:String): TradingInformationModel

    suspend fun getOrderBook(book:String): OrderbookModel

}