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
import com.example.readbitso.support.icon
import com.example.readbitso.support.operationKind
import com.example.readbitso.support.shortToken
import com.example.readbitso.support.tokens
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    val errorMessage: StateFlow<String > =_errorMessage

    private val _isLoading = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading

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
                                        if (trades.isNotEmpty() and openedPayloadsCoin.isNotEmpty())
                                            _uiState.value = true
                                        //
                                        delay(500)
                                        getBidsAsk(str, message = "ok")
                                        getnewListTrades(getTrades(name = str))
                                        delay(2000)
                                        //
                                        extensionLambda { insertDbAskBids() }
                                        delay(500)
                                        extensionLambda { insertDbTrades() }
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
        bitsoRepositoryImp.getRfBitsoBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { message -> processBooks(message.payload, errorMessage = "ok") },
                { error ->
                    viewModelScope.launch {
                        processBooks(bitsoRepositoryImp.internetError(error))
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
                    maximum_price = it.maximum_price.take(10),
                    minimum_price = it.minimum_price.take(10)
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
                    _errorMessage.value = bitsoRepositoryImp.getInternetFlag("wifi").toString()
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

        detailedPayload = _detailedPayload

        _isLoading.value = returnList.isNotEmpty()
        return returnList
    }

    private suspend fun getBidsAsk(name: String, message: String) {
        viewModelScope.launch(IO) {
            bitsoRepositoryImp.getRfBitsoBids(name)
                .catch { }
                .collect {
                    _openedPayloadsCoin = it
                }
            openedPayloadsCoin = _openedPayloadsCoin
        }
        setError(message)
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
        trades = getnewListTrades(_trades)
        return _trades
    }

    private fun getnewListTrades(openedPayloads: List<PayloadTrades>): List<PayloadTrades> {
        val returnList = mutableListOf<PayloadTrades>()
        openedPayloads.forEachIndexed { _, it ->
            returnList.add(
                PayloadTrades(
                    amount = it.amount.take(10),
                    book = it.book,
                    maker_side = operationKind(it.maker_side),
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
            bitsoRepositoryImp.selectActualToken("name", coin)
        }
    }

    private suspend fun getCoin(): String? {
        viewModelScope.launch {
            selectedCoin = bitsoRepositoryImp.getActualToken("name")
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
            .map {
                it.map {
                    PayloadTrades(
                        amount = it.Amount.toString(),
                        book = it.pair.toString(),
                        maker_side = operationKind(it.Type.toString()),
                        price = it.Price.toString(),
                    )
                }
            }
            .collect {
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
                it.map{ AB->
                    PayloadTickers(
                    ask = AB.Ask,
                    bid = AB.Bid,
                    book = AB.Book)
                 }
            }
            .collect { it.forEach { returnList.add(it) }
            }

        _openedPayloadsCoin = returnList
        lastconsume = tokens(returnList[0].book.toString())

        openedPayloadsCoin = _openedPayloadsCoin
    }
}


