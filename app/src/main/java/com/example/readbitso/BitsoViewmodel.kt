package com.example.readbitso

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capproject.support.icon
import com.example.capproject.support.shortToken
import com.example.readbitso.models.bitsoBooks.BooksPayload
import com.example.readbitso.models.bitsoBooks.DetailedPayload
import com.example.readbitso.repository.BitsoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class BitsoViewmodel
@Inject constructor(private val bitsoRepositoryImp: BitsoRepository): ViewModel() {

    var status=MutableStateFlow<Boolean>(false)
    //    private var books:List
    private var openedPayloads: List<BooksPayload> by mutableStateOf(listOf())
    var detailedPayload: List<DetailedPayload> by mutableStateOf(listOf())

    var errormessage:String  by mutableStateOf("")
    var isloading: Boolean by mutableStateOf(false)

    init {
        viewModelScope.launch {

            while(true)
            {
                readResponse()
                delay(5000)
                getBooks(openedPayloads)
            }
        }
    }

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
        when (error){
            is UnknownHostException ->errormessage="Pagina no disponible"
            is RuntimeException -> errormessage="Runtime Exception"
            else -> println(error)
        }
    }

    //
    private fun processBooks(book: List<BooksPayload>)
    {
        val returnlist = mutableListOf<BooksPayload>()
        book.forEachIndexed {index,it->
            returnlist.add(BooksPayload(
                id = index,
                book=it.book,
                maximum_price = it.maximum_price.take(10),
                minimum_price=it.minimum_price.take(10)))
        }
        openedPayloads=returnlist
    }


    fun getBooks(openedPayloads: List<BooksPayload>) = GetnewList(openedPayloads)



    private fun GetnewList(openedPayloads: List<BooksPayload>): List<DetailedPayload> {
        val returnlist = mutableListOf<DetailedPayload>()
        openedPayloads.forEach {
            returnlist.add(DetailedPayload(payload = it,
                shortname = shortToken(it.book),
                icon = icon(it.book)
            ))
        }
         detailedPayload=returnlist
        isloading=returnlist.isNotEmpty()
        return returnlist
    }
}