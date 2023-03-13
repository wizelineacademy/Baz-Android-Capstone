package com.example.wizelineandroid.presentation.books

import androidx.lifecycle.*
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.local.entitys.BookEntity
import com.example.wizelineandroid.data.remote.model.ModelBook
import com.example.wizelineandroid.repository.available.BookRoomRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookRoomViewModel @Inject constructor(private val bookDao: BookRoomRepo) : ViewModel() {

    fun getBooks() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(bookDao.getBooks()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    private fun insertItem(bookEntity: List<BookEntity>) {
        viewModelScope.launch { bookDao.insertBooks(bookEntity) }
    }

    private fun getNewItemEntry(itemName: List<ModelBook>): List<BookEntity> {
        val entities = itemName.map { currency ->
            BookEntity(
                itemName = currency.book,
                id = currency.book,
                maximum_price = currency.maximum_price,
                minimum_price = currency.minimum_price
            )
        }
        return entities
    }

    @Singleton
    fun addNewItem(itemName: List<ModelBook>) {
        itemName.toObservable().subscribeBy( // named arguments for lambda Subscribers
            onNext = { println(it) },
            onError = { it.printStackTrace() },
            onComplete = {
                val newItem = getNewItemEntry(itemName)
                insertItem(newItem)
                println("Done!")
            }
        )
    }

    fun isEntryValid(ticker: String): Boolean {
        return ticker.isNotBlank()
    }
}
