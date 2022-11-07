package wizeline.crypto.currency.domain.useCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import wizeline.crypto.currency.data.Result
import wizeline.crypto.currency.domain.model.OrderbookModel
import wizeline.crypto.currency.domain.model.TradingInformationModel
import wizeline.crypto.currency.domain.repositories.CryptoCurrenciesRepository
import java.io.IOException
import javax.inject.Inject

class OrderBookUseCase @Inject constructor(
    private var repository:CryptoCurrenciesRepository
) {
    suspend operator fun invoke(book:String): Flow<Result<OrderbookModel>> = flow {

        try {
            val response= repository.getOrderBook(book)
            emit(Result.Success(response))

        }catch (e: HttpException){
            emit(
                Result.Error(
                    message = "Opps algo sucedio",
                    data =null
                ))
        }catch (e: IOException){
            emit(
                Result.Error(
                    message = "Opps sin conexión ",
                    data =null
                ))
        }

    }


}