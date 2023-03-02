package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.useCases

import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.toDomain
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.response.ExchangeOrderBookResponse
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book.LocalOrderBookRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.views.uiStates.BooksScreenState
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetBooksUseCase(
    private val remoteOrderBooksRepository: RemoteOrderBooksRepository,
    private val localOrderBookRepository: LocalOrderBookRepository,
) {
    suspend operator fun invoke(): BooksScreenState {
        val booksWrappedResponse = remoteOrderBooksRepository.getBooksFromApi()
        return when (booksWrappedResponse) {
            is BitsoApiResponseWrap.Success -> {
                val booksArray = booksWrappedResponse.response.payload
                if (booksArray.isNullOrEmpty()) {
                    BooksScreenState.BooksErrorOrEmpty()
                } else {
                    val availableExchangeOrderBooks = ArrayList(
                        booksArray.map { it.toDomain() },
                    )
                    updateDBAvailableExchangeOrderBook(availableExchangeOrderBooks)
                    BooksScreenState.BooksSuccess(availableExchangeOrderBooks)
                }
            }
            else -> {
                BooksScreenState.BooksErrorOrEmpty()
            }
        }
    }

    fun callAvailableOrderBooksRX(
        action: (state: BooksScreenState) -> Unit,
    ) {
        remoteOrderBooksRepository.getAvailableOrderBookRX()
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<BitsoBaseResponse<ArrayList<ExchangeOrderBookResponse>>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(response: BitsoBaseResponse<ArrayList<ExchangeOrderBookResponse>>) {
                    if (response.success == true && response.payload != null && response.payload.isNotEmpty()) {
                        val payloadDomain = response.payload
                        val availableExchangeOrderBooks =
                            payloadDomain.map { it.toDomain() } as ArrayList<ExchangeOrderBook>
                        CoroutineScope(Dispatchers.IO).launch {
                            updateDBAvailableExchangeOrderBook(availableExchangeOrderBooks)
                        }
                        action(BooksScreenState.BooksSuccess(availableExchangeOrderBooks))
                    } else {
                        action(BooksScreenState.BooksErrorOrEmpty())
                    }
                }

                override fun onError(e: Throwable) {
                    log("z0", "error")
                    action(BooksScreenState.BooksErrorOrEmpty())
                }

                override fun onComplete() {
                }
            })
    }

    suspend fun retrieveExchangeOrderBook(): BooksScreenState {
        val localExchangeOrderBooks = localOrderBookRepository.retrieveExchangeOrderBooks()
        return if (localExchangeOrderBooks.isNotEmpty()) {
            BooksScreenState.BooksSuccess(localExchangeOrderBooks)
        } else {
            BooksScreenState.BooksErrorOrEmpty()
        }
    }

    private suspend fun updateDBAvailableExchangeOrderBook(availableExchangeOrderBook: ArrayList<ExchangeOrderBook>) {
        localOrderBookRepository.storeAvailableExchangeOrderBooks(availableExchangeOrderBook)
    }
}
