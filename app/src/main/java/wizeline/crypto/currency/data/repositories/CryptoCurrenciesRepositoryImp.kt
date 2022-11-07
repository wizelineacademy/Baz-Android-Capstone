package wizeline.crypto.currency.data.repositories

import wizeline.crypto.currency.data.resource.remote.CryptoCurrenciesApi
import wizeline.crypto.currency.domain.repositories.CryptoCurrenciesRepository
import wizeline.crypto.currency.data.models.convertToListBooks
import wizeline.crypto.currency.data.models.convertToTradingInformation
import wizeline.crypto.currency.data.models.toListOrderBook
import wizeline.crypto.currency.domain.model.AvailableBooksModel
import wizeline.crypto.currency.domain.model.OrderbookModel
import wizeline.crypto.currency.domain.model.TradingInformationModel
import javax.inject.Inject

class CryptoCurrenciesRepositoryImp @Inject constructor(
    private val api: CryptoCurrenciesApi
) : CryptoCurrenciesRepository {

    override suspend fun getAvailableBooks(): List<AvailableBooksModel> {
       return  api.getAvailableBooks().convertToListBooks()
    }

    override suspend fun getInformationTrading(book:String): TradingInformationModel {
        return api.getInformationTrading(book).convertToTradingInformation()
    }

    override suspend fun getOrderBook(book: String): OrderbookModel {
        return api.getOrderBook(book).toListOrderBook()
    }

}
