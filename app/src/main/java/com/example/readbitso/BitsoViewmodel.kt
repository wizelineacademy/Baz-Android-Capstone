package com.example.readbitso

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readbitso.support.icon
import com.example.readbitso.support.shortToken
import com.example.readbitso.models.bitsoModels.bitsoBooks.BooksPayload
import com.example.readbitso.models.bitsoModels.bitsoBooks.DetailedPayload
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.PayloadTickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.PayloadTrades
import com.example.readbitso.repository.BitsoRepository
import com.example.readbitso.repository.datastore.DataStoreRepository
import com.example.readbitso.support.loggerD
import com.example.readbitso.support.operation
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
@Inject constructor(private val bitsoRepositoryImp: BitsoRepository, private val dsRepository: DataStoreRepository
): ViewModel() {


    private var openedPayloads: List<BooksPayload> by mutableStateOf(listOf())
    var detailedPayload: List<DetailedPayload> by mutableStateOf(listOf())
    var trades: List<PayloadTrades> by mutableStateOf(listOf())
    var state: String by mutableStateOf("")
    var errormessage: String by mutableStateOf("")
    var isloading: Boolean by mutableStateOf(false)
    var openedPayloadsCoin: List<PayloadTickers> by mutableStateOf(listOf())

    init {
        state="first"
        selectPage("first")

        viewModelScope.launch {
            while (true) {

                getPage()?.let{
                    state=it
                }

                when (state) {
                    "first" -> {
                        readResponse()
                        getBooks(openedPayloads)
                        delay(5000)
                        //println(getbdTokens("mxn"))
                    }
                    "second" -> {
                        getCoin()?.let{
                            withContext(Dispatchers.IO){
                                getBidsAsk(it)
                                delay(1000)
                                getTrades(it)
                                delay(2000)
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    //-------------------
    //Servicios con RxJava
    private fun readResponse() {
        bitsoRepositoryImp.getBitsoBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { message -> processBooks(message.payload) },
                { error -> displayError(error) }
            )
    }

    private fun displayError(error: Throwable) {
        when (error) {
            is UnknownHostException -> errormessage = "Pagina no disponible"
            is RuntimeException -> errormessage = "Runtime Exception"
            is SocketTimeoutException -> errormessage = "SocketTimeout"
            else -> println(error)
        }
    }
    //----------------------

    private fun processBooks(book: List<BooksPayload>) {
        val returnlist = mutableListOf<BooksPayload>()
        book.forEachIndexed { index, it ->
            returnlist.add(BooksPayload(
                id = index,
                book = it.book,
                maximum_price = it.maximum_price.take(10),
                minimum_price = it.minimum_price.take(10)))
        }
        openedPayloads = returnlist
    }


    private suspend fun getBooks(openedPayloads: List<BooksPayload>) = GetnewList(openedPayloads)


    private suspend fun GetnewList(openedPayloads: List<BooksPayload>): List<DetailedPayload> {
        val returnlist = mutableListOf<DetailedPayload>()
        openedPayloads.forEach {
            returnlist.add(DetailedPayload(payload = it,
                shortname = shortToken(it.book),
                icon = icon(it.book)
            ))

        }
        detailedPayload = returnlist.filter {
            it.payload.book.contains("mxn")
        }
        insertTokens()
        isloading = returnlist.isNotEmpty()
        return returnlist
    }

    private suspend fun getBidsAsk(name: String) {
        viewModelScope.launch {
            bitsoRepositoryImp.getBitsoBids(name)
                .catch { }
                .collect {
                    openedPayloadsCoin=it
                }
        }
    }


    private suspend fun getTrades(name: String) {
        viewModelScope.launch {
            bitsoRepositoryImp.getBitsoTrades(name)
                .catch { }
                .take(15)
                .collect {
                    trades= GetnewListTrades(it)
                }
        }
    }

    private fun GetnewListTrades(openedPayloads: List<PayloadTrades>): List<PayloadTrades> {
        val returnlist = mutableListOf<PayloadTrades>()
        openedPayloads.forEach {
            returnlist.add(PayloadTrades(
                amount=it.amount.take(10),
                book=it.book,
                maker_side = operation(it.maker_side),
                price=it.price.take(10)
            ))
        }
        isloading = returnlist.isNotEmpty()
        return returnlist
    }

    //------------
    //DataStore
    fun setCoin(coin: String) {
        viewModelScope.launch {
            dsRepository.selectCoin("name", coin)
        }
    }
    fun getCoin(): String? = runBlocking {
        dsRepository.getCoin("name")
    }
    fun selectPage(Page: String) = viewModelScope.launch {
        dsRepository.setPage("page", Page)
    }
    fun getPage(): String? = runBlocking {
        dsRepository.getPage("page")
    }
    ///
//----------


    //room
    private suspend fun insertTokens()
    {
        bitsoRepositoryImp.insertBooks(openedPayloads)
    }

    private suspend fun getbdTokens(filter: String) {

        var returnlist = mutableListOf<BooksPayload>()
        bitsoRepositoryImp.getflowBooks()
            .collect { it ->
                it.forEach { moneda->
                    returnlist.add(BooksPayload(
                        book = moneda.book,
                        id = moneda.id,
                        maximum_price = moneda.maximum_price,
                        minimum_price=moneda.minimum_price)) }
            }

        openedPayloads = returnlist.filter {
            it.book.contains(filter)
        }
    }
}