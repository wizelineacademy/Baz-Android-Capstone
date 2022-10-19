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
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _uiError = MutableStateFlow(ServicesResponse.Error(Throwable()))
    private val uiError: StateFlow<ServicesResponse> = _uiError

    private val _isLoading = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading

    private val _uiAskBidState = MutableStateFlow(ServicesResponse.AskBidsConsumeSuccess(emptyList()))
    private val uiStateAskBidsState: StateFlow<ServicesResponse> = _uiAskBidState

    private val _uiTradesState = MutableStateFlow(ServicesResponse.TradingBooksConsumeSuccess(emptyList()))
    private val uiTradesState: StateFlow<ServicesResponse> = _uiTradesState


    private var _openedPayloadsCoin: List<PayloadTickers> by mutableStateOf(listOf())
    var openedPayloadsCoin: List<PayloadTickers> by mutableStateOf(_openedPayloadsCoin)


    private var selectedCoin: String? by mutableStateOf("")

    var lastconsume: String by mutableStateOf("")

    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState


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
                        _uiState.value = false
                        readResponse()
                        delay(3000)
                    }
                    "second" -> {
                        getCoin()?.let { str ->
                            withContext(IO) {

                                when (_errorMessage.value) {
                                    "" -> {
                                        delay(500)
                                        _trades = getTrades(str)
                                        getBidsAsk(str, "ok")
                                        delay(2000)

                                        when (uiTradesState.value) {
                                            is ServicesResponse.TradingBooksConsumeSuccess -> {
                                                trades = getnewListTrades(_trades)
                                                extensionLambda { insertDbTrades() }
                                                _uiState.value = true
                                            }
                                        }

                                        when (uiStateAskBidsState.value) {
                                            is ServicesResponse.TradingBooksConsumeSuccess -> {
                                                openedPayloadsCoin = _openedPayloadsCoin
                                                extensionLambda { insertDbAskBids() }
                                            }
                                        }

                                        when (uiError.value) {
                                            is ServicesResponse.Error -> {
                                                when (_uiError.value.exception) {
                                                    is UnknownHostException -> loggerD(this@BitsoViewmodel._uiError.value.toString())
                                                    else -> {
                                                        loggerD("All it's Ok ")
                                                    }
                                                }
                                            }
                                        }
                                        delay(500)
                                    }

                                    "Datos no disponibles" -> {
                                        setError("--")
                                        getDbAskBids()
                                        trades = getnewListTrades((getDbTrades()))
                                        if (trades.isNotEmpty() && openedPayloadsCoin.isNotEmpty())
                                            _uiState.value = true
                                        delay(3000)
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
        bitsoRepositoryImp.getRetrofitSingleBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { message -> processBooks(message.payload, errorMessage = "ok") },
                { error ->
                    viewModelScope.launch {
                        processBooks(bitsoRepositoryImp.getInternetErrorResponse(error))
                    }
                })
    }


    private fun processBooks(book: List<BooksPayload>, errorMessage: String = "") {

        setError(errorMessage)

        val returnList = mutableListOf<BooksPayload>()
        book.forEachIndexed { index, it ->
            returnList.add(
                BooksPayload(
                    id = index,
                    book = it.book,
                    maximumPrice = it.maximumPrice.take(10),
                    minimumPrice = it.minimumPrice.take(10)
                )
            )
        }
        openedPayloads = returnList

        viewModelScope.launch(IO) {
            extensionLambda { insertDbTokens() }
        }
        getNewBooksList(openedPayloads)
    }

    private fun setError(errorMessage: String) {
        when (errorMessage) {
            "ok" -> {
                _errorMessage.value = ""

            }
            else -> {
                viewModelScope.launch {
                    _errorMessage.value = bitsoRepositoryImp.getInternetError("wifi").toString()
                }
            }
        }

    }


    private inline fun extensionLambda(block: () -> Unit) = block()


    private fun getNewBooksList(
        openedPayloads: List<BooksPayload>,
        pair: String = "mxn",
    ): List<DetailedPayload> {
        val returnList = mutableListOf<DetailedPayload>()
        openedPayloads
            .map {
                DetailedPayload(
                    payload = it,
                    shortName = shortToken(it.book),
                    icon = icon(it.book)
                )
            }
            .forEach {
                returnList.add(it)
            }

        _detailedPayload = returnList.filter {
            it.payload.book.contains(pair)
        }

        detailedPayload = _detailedPayload

        _isLoading.value = returnList.isNotEmpty()
        return returnList
    }

    private suspend fun getBidsAsk(name: String, message: String): List<PayloadTickers> {
        viewModelScope.launch(IO) {
            bitsoRepositoryImp.getRetrofitResponseAskBids(name)
                .catch {}
                .map { it.body()?.payload }
                .collect {
                    it?.let {
                        _openedPayloadsCoin = listOf(it)
                    }
                }
        }
        setError(message)
        openedPayloadsCoin=_openedPayloadsCoin
        return _openedPayloadsCoin
    }

    private suspend fun getTrades(name: String): List<PayloadTrades> {
        viewModelScope.launch {
            bitsoRepositoryImp.getRetrofitResponseTrades(name)
                .catch {
                 }
                .map { it.body()?.payload }
                .take(15)
                .collect {
                    it?.let {
                        _uiTradesState.value = ServicesResponse.TradingBooksConsumeSuccess(it)
                        _trades = it
                    }
                }
        }
        return _trades
    }

    private fun getnewListTrades(openedPayloads: List<PayloadTrades>): List<PayloadTrades> {
        val returnList = mutableListOf<PayloadTrades>()
        openedPayloads.forEachIndexed { _, it ->
            returnList.add(
                PayloadTrades(
                    amount = it.amount.take(10),
                    book = it.book,
                    makerSide = operationKind(it.makerSide),
                    price = it.price.take(10)
                )
            )
        }

        _isLoading.value = returnList.isNotEmpty()
        isLoading = _isLoading
        return returnList
    }

    fun setCoin(coin: String) {
        viewModelScope.launch {
            bitsoRepositoryImp.selectCurrency("name", coin)
        }
    }

    private suspend fun getCoin(): String? {
        viewModelScope.launch {
            selectedCoin = bitsoRepositoryImp.getSelectedCurrency("name")
        }
        if (_errorMessage.value == "")
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


    private suspend fun insertDbTrades() =
        bitsoRepositoryImp.insertDbTrades(_trades)

    private suspend fun getDbTrades(): MutableList<PayloadTrades> {
        val returnList = mutableListOf<PayloadTrades>()
        bitsoRepositoryImp.getDbTrades()
            .catch { }
            .map { it ->
                it.map {
                    PayloadTrades(
                        amount = it.amount.toString(),
                        book = it.pair.toString(),
                        makerSide = operationKind(it.type.toString()),
                        price = it.price.toString(),
                    )
                }
            }
            .collect { it ->
                it.forEach { returnList.add(it) }
            }
        _trades = returnList

        return returnList
    }

    private suspend fun insertDbAskBids() =
        bitsoRepositoryImp.insertDbAsk(_openedPayloadsCoin)

    private suspend fun getDbAskBids() {
        val returnList = mutableListOf<PayloadTickers>()

        bitsoRepositoryImp.getDbAskBids()
            .catch { }
            .map {
                it.map { AB ->
                    PayloadTickers(
                        ask = AB.ask,
                        bid = AB.bid,
                        book = AB.book)
                }
            }
            .collect { it ->
                it.forEach { returnList.add(it) }
            }

        _openedPayloadsCoin = returnList
        lastconsume = tokens(returnList[0].book.toString())

        openedPayloadsCoin = _openedPayloadsCoin
    }
}



sealed class ServicesResponse {
    data class AskBidsConsumeSuccess(val Askbid: List<PayloadTickers>) : ServicesResponse()
    data class TradingBooksConsumeSuccess(val trades: List<PayloadTrades>) : ServicesResponse()
    data class Error(val exception: Throwable) : ServicesResponse()
}








