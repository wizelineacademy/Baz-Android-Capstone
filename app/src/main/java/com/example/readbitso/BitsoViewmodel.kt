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
@Inject constructor(private val bitsoRepositoryImp: BitsoRepository) : ViewModel() {
    private var openedPayloads: List<BooksPayload> by mutableStateOf(listOf())
    private var _detailedPayload: List<DetailedPayload> by mutableStateOf(listOf())
    var detailedPayload: List<DetailedPayload> by mutableStateOf(_detailedPayload)

    private var _trades: List<PayloadTrades> by mutableStateOf(listOf())
    var trades: List<PayloadTrades> by mutableStateOf(_trades)

    private var page: String? by mutableStateOf("")

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _uiError = MutableStateFlow(ErrorState.Error(Throwable()))
    private val uiError: StateFlow<ErrorState> = _uiError

    private val _isLoading = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading

    private val _uiAskBidState = MutableStateFlow(LatestAskBidsState.Success(emptyList()))
    private val uiStateAskBidsState: StateFlow<LatestAskBidsState> = _uiAskBidState

    private val _uiTradesState = MutableStateFlow(LatestTradesState.Success(emptyList()))
    private val uiTradesState: StateFlow<LatestTradesState> = _uiTradesState

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
                                            is LatestTradesState.Success -> {
                                                trades = getnewListTrades(_trades)
                                                extensionLambda { insertDbTrades() }
                                                _uiState.value = true
                                            }
                                        }

                                        when (uiStateAskBidsState.value) {
                                            is LatestAskBidsState.Success -> {
                                                openedPayloadsCoin = _openedPayloadsCoin
                                                extensionLambda { insertDbAskBids() }
                                            }
                                        }

                                        when (uiError.value) {
                                            is ErrorState.Error -> {
                                                when (_uiError.value.exception) {
                                                    is UnknownHostException -> loggerD(_uiError.value.toString())
                                                    else -> {
                                                        loggerD(" Todo ok ")
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

    private suspend fun getBidsAsk(name: String, message: String): List<PayloadTickers> {
        viewModelScope.launch(IO) {
            bitsoRepositoryImp.getRfBitsoBids(name)
                .catch {}
                .map { it.body()?.payload }
                .collect {
                    it?.let {
                        _openedPayloadsCoin = listOf(it)
                        _uiAskBidState.value = LatestAskBidsState.Success(listOf(it))
                    }
                }
        }
        setError(message)
        return _openedPayloadsCoin
    }

    private suspend fun getTrades(name: String): List<PayloadTrades> {
        viewModelScope.launch {
            bitsoRepositoryImp.getRfBitsoTrades(name)
                .catch {
                    when (it) {
                        is UnknownHostException -> {
                            it.let { _uiError.value = ErrorState.Error(it.cause!!) }
                        }
                        else -> {
                            _uiError.value = ErrorState.Error(it.cause!!)
                        }
                    }
                }
                .map { it.body()?.payload }
                .take(15)
                .collect {
                    it?.let {
                        _uiTradesState.value = LatestTradesState.Success(it)
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
            .map { it ->
                it.map {
                    PayloadTrades(
                        amount = it.Amount.toString(),
                        book = it.pair.toString(),
                        maker_side = operationKind(it.Type.toString()),
                        price = it.Price.toString(),
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
                        ask = AB.Ask,
                        bid = AB.Bid,
                        book = AB.Book)
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


sealed class LatestAskBidsState {
    data class Success(val Askbid: List<PayloadTickers>) : LatestAskBidsState()
}

sealed class LatestTradesState {
    data class Success(val trades: List<PayloadTrades>) : LatestTradesState()
}

sealed class ErrorState {
    data class Error(val exception: Throwable) : ErrorState()
}








