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
@Inject constructor(private val bitsoRepositoryImp: BitsoRepository
): ViewModel() {


    private var openedPayloads: List<BooksPayload> by mutableStateOf(listOf())
    var detailedPayload: List<DetailedPayload> by mutableStateOf(listOf())
    var trades: List<PayloadTrades> by mutableStateOf(listOf())
    var state: String? by mutableStateOf("")
    var errormessage: String by mutableStateOf("")
    var isloading: Boolean by mutableStateOf(false)
    var openedPayloadsCoin: List<PayloadTickers> by mutableStateOf(listOf())
    var lastname:String by mutableStateOf("")
    private var SelectedCoin:String? by mutableStateOf("")



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

                        //getbdTokens("mxn")

                        delay(5000)

                      //  loggerD("${getbdTokens("mxn")}")
                    }
                    "second" -> {
                        getCoin()?.let{
                            withContext(Dispatchers.IO){
                                getBidsAsk(it)
                                getTrades(it)
                                //-----

                                //-----
                                delay(2000)
                            //      getAskBids()
                            //    getDbTrades()
                            //  delay(5000)

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


    private suspend fun getBooks(openedPayloads: List<BooksPayload>) = getnewList(openedPayloads)


    private suspend fun getnewList(openedPayloads: List<BooksPayload>): List<DetailedPayload> {
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
        insertdbTokens()
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
        insertdbAskBids()
    }


    private suspend fun getTrades(name: String) {
        viewModelScope.launch {
            bitsoRepositoryImp.getBitsoTrades(name)
                .catch { }
                .take(15)
                .collect {
                    trades= GetnewListTrades(it)
                }
            insertdbTrades()
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
            bitsoRepositoryImp.selectCoin("name",coin)
        }
    }

    private suspend fun getCoin(): String? {
        viewModelScope.launch {
            SelectedCoin=bitsoRepositoryImp.getCoin("name")
        }
        lastname=tokens(SelectedCoin.toString())
        return SelectedCoin
    }


    fun selectPage(Page: String) = viewModelScope.launch {
        bitsoRepositoryImp.setPage("page", Page)
    }

    private suspend fun getPage(): String? {
        viewModelScope.launch{
            state= bitsoRepositoryImp.getPage("page")
        }
        return state
    }


    //room
    //----------------------
    private suspend fun insertdbTokens()
    {
        bitsoRepositoryImp.insertBooks(openedPayloads)
    }

    //Actives list
    private suspend fun getbdTokens(filter: String ="mxn"): List<BooksPayload> {
        val returnlist = mutableListOf<BooksPayload>()
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
        return openedPayloads
    }


    private suspend fun insertdbTrades()
    {
        bitsoRepositoryImp.insertTrades(trades)
    }

    private suspend fun getdbTrades()
    {
        bitsoRepositoryImp.getflowTrades()
            .catch {  }
            .collect {
                println(it)
            }
    }

    private suspend fun insertdbAskBids()
    {
        bitsoRepositoryImp.insertAsk(openedPayloadsCoin)
    }

    private suspend fun getdbAskBids()
    {
        bitsoRepositoryImp.getflowAskBids()
            .catch {  }
            .collect {
                println(it)
            }
    }
    //-----
}