package wizeline.crypto.currency.domain.useCase

import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import wizeline.crypto.currency.domain.repositories.CryptoCurrenciesRepository
import wizeline.crypto.currency.data.Result
import wizeline.crypto.currency.domain.model.AvailableBooksModel
import java.io.IOException

import javax.inject.Inject

class AvailableBookUseCase @Inject constructor(
    private val repository: CryptoCurrenciesRepository
) {

    suspend operator fun invoke(): Flow<Result<List<AvailableBooksModel>>> = flow<Result<List<AvailableBooksModel>>> {

        try {
            val response= repository.getAvailableBooks()
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