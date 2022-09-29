package com.example.readbitso

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readbitso.models.bitsoModels.bitsoBooks.BooksPayload
import com.example.readbitso.models.bitsoModels.bitsoBooks.DetailedPayload
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.PayloadTickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.PayloadTrades
import com.example.readbitso.repository.BitsoRepository
import com.example.readbitso.support.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.take
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class BitsoViewmodel
@Inject constructor(private val bitsoRepositoryImp: BitsoRepository) : ViewModel()
{
    private var openedPayloads: List<BooksPayload> by mutableStateOf(listOf())
    private var _detailedPayload: List<DetailedPayload> by mutableStateOf(listOf())
    var detailedPayload: List<DetailedPayload> by mutableStateOf(_detailedPayload)

    private var _trades: List<PayloadTrades> by mutableStateOf(listOf())
    var trades: List<PayloadTrades> by mutableStateOf(_trades)

    private var page: String? by mutableStateOf("")

    private var _errorMessage: String by mutableStateOf("")
    var errorMessage:String by mutableStateOf(_errorMessage)

    private var _isLoading: Boolean by mutableStateOf(false)
    var isLoading: Boolean by mutableStateOf(_isLoading)

    private var _openedPayloadsCoin: List<PayloadTickers> by mutableStateOf(listOf())
    var openedPayloadsCoin: List<PayloadTickers> by mutableStateOf(_openedPayloadsCoin)

    private var selectedCoin: String? by mutableStateOf("")

    private var _update: Boolean by mutableStateOf(false)
    var update:Boolean by mutableStateOf(_update)

    var lastconsume: String by mutableStateOf("")

    init {
        page = "first"
        selectPage("first")

        viewModelScope.launch {
            while (true) {

                getPage()?.let {
                    page = it
                }

                when (page) {
                    "first" -> {
                        readResponse()
                        when (_errorMessage) {
                            "" -> {
                                extensionLambda { getNewBooksList(openedPayloads, "mxn") }
                                if (_detailedPayload.isNotEmpty())
                                    extensionLambda { insertDbTokens() }
                            }
                            "Pagina no disponible" -> {
                                _errorMessage = "Datos no disponibles"
                                extensionLambda { getNewBooksList(getDdTokens()) }
                            }
                            else -> { println(_errorMessage) }
                        }
                        delay(3000)
                    }
                    "second" -> {
                        getCoin()?.let { str ->
                            withContext(Dispatchers.IO) {
                                when (_errorMessage) {
                                    "" -> {
                                        delay(2000)
                                        getBidsAsk(str)
                                        delay(1000)
                                        extensionLambda{insertDbAskBids()}
                                        delay(500)
                                        extensionLambda{insertDbTrades()}
                                    }
                                    "Pagina no disponible" -> {
                                        _errorMessage = "Datos no disponibles"
                                        delay(3000)
                                        extensionLambda { getDbAskBids() }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun readResponse() {
        bitsoRepositoryImp.getRfBitsoBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { message -> processBooks(message.payload) },
                { error -> displayError(error) }
            )
    }

    private fun displayError(error: Throwable) {
        when (error) {
            is UnknownHostException -> _errorMessage = "Pagina no disponible"
            is RuntimeException -> _errorMessage = "Runtime Exception"
            is SocketTimeoutException -> _errorMessage = "SocketTimeout"
            else -> println(error)
        }
    }

    private fun processBooks(book: List<BooksPayload>) {
        _errorMessage = ""
        val returnList = mutableListOf<BooksPayload>()
        book.forEachIndexed { index, it ->
            returnList.add(
                BooksPayload(
                    id = index,
                    book = it.book,
                    maximum_price = it.maximum_price.take(10),
                    minimum_price = it.minimum_price.take(10)
                )
            )
        }
        openedPayloads = returnList
    }

    private inline fun extensionLambda(block: () -> Unit) = block()

    private fun getNewBooksList(openedPayloads: List<BooksPayload>, pair: String = "mxn"): List<DetailedPayload>
    {
        val returnList = mutableListOf<DetailedPayload>()
        openedPayloads
            .map{
                DetailedPayload(
                    payload = it,
                    shortname = shortToken(it.book),
                    icon = icon(it.book)
                )
            }
            .forEach {
                returnList.add(it)
            }

        _detailedPayload = returnList.filter {
            it.payload.book.contains(pair)
        }

        detailedPayload=_detailedPayload

        _isLoading = returnList.isNotEmpty()
        isLoading=_isLoading
        return returnList
    }

    private suspend fun getBidsAsk(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            bitsoRepositoryImp.getRfBitsoBids(name)
                .catch { }
                .collect {
                    _openedPayloadsCoin = it
                }
            extensionLambda { getnewListTrades(getTrades(name = name)) }
            openedPayloadsCoin = _openedPayloadsCoin
        }
    }

    private suspend fun getTrades(name: String): List<PayloadTrades> {
        viewModelScope.launch {
            bitsoRepositoryImp.getRfBitsoTrades(name)
                .catch { }
                .take(15)
                .collect {
                    _trades = it
                }
        }
        trades=getnewListTrades(_trades)
        return _trades
    }

    private fun getnewListTrades(openedPayloads: List<PayloadTrades>): List<PayloadTrades> {
        val returnList = mutableListOf<PayloadTrades>()
        openedPayloads.forEachIndexed { _, it->
            returnList.add(
                PayloadTrades(
                    amount = it.amount.take(10),
                    book = it.book,
                    maker_side = operationKind(it.maker_side),
                    price = it.price.take(10)
                )
            )
        }
        _isLoading = returnList.isNotEmpty()
        update=returnList.isNotEmpty()
        return returnList
    }

    fun setCoin(coin: String)
    {
        viewModelScope.launch {
            bitsoRepositoryImp.selectActualToken("name", coin)
        }
    }

    private suspend  fun getCoin(): String?
    {
        viewModelScope.launch {
            selectedCoin = bitsoRepositoryImp.getActualToken("name")
        }
        if (_errorMessage == "")
            lastconsume = tokens(selectedCoin.toString())

        return selectedCoin
    }

    fun selectPage(Page: String) =
        viewModelScope.launch {
            bitsoRepositoryImp.setActualView("page", Page)
        }

    private suspend fun getPage(): String? {
        viewModelScope.launch {
            page = bitsoRepositoryImp.getActualView("page")
        }
        return page
    }

    private suspend fun insertDbTokens() =
        bitsoRepositoryImp.insertDbBooks(openedPayloads)

    private suspend fun getDdTokens(): MutableList<BooksPayload>
    {
        val returnList = mutableListOf<BooksPayload>()
        bitsoRepositoryImp.getDbBooks()
            .collect {
                it.forEach { currencies ->
                    returnList.add(
                        BooksPayload(
                            book = currencies.book,
                            id = currencies.id,
                            maximum_price = currencies.maximum_price,
                            minimum_price = currencies.minimum_price
                        )
                    )
                }
            }
        return returnList
    }

    private suspend fun insertDbTrades() =
        bitsoRepositoryImp.insertDbTrades(_trades)

    private suspend fun getDbTrades(): MutableList<PayloadTrades>
    {
        val returnList = mutableListOf<PayloadTrades>()
        bitsoRepositoryImp.getDbTrades()
            .catch { }
            .collect { it ->
                it.forEach {
                    returnList.add(
                        PayloadTrades(
                            amount = it.Amount.toString(),
                            book = it.pair.toString(),
                            maker_side = operationKind(it.Type.toString()),
                            price = it.Price.toString(),
                        )
                    )
                }
                _trades = returnList
            }
        return returnList
    }

    private suspend fun insertDbAskBids() =
        bitsoRepositoryImp.insertDbAsk(_openedPayloadsCoin)

    private suspend fun getDbAskBids() {
        val returnList = mutableListOf<PayloadTickers>()

        bitsoRepositoryImp.getDbAskBids()
            .catch { }
            .collect { it ->
                it.forEach {
                    returnList.add(
                        PayloadTickers(
                            ask = it.Ask,
                            bid = it.Bid,
                            book = it.Book
                        )
                    )
                }
            }
        _openedPayloadsCoin = returnList
        lastconsume = tokens(returnList[0].book.toString())
        _isLoading = returnList.isNotEmpty()
        extensionLambda { getnewListTrades(getDbTrades()) }
    }

}
