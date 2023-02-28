package com.example.wizelineandroid.presentation.books

import androidx.lifecycle.*
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.local.dao.BookDao
import com.example.wizelineandroid.data.local.entitys.BookEntity
import com.example.wizelineandroid.data.remote.model.ModelBook
import com.example.wizelineandroid.repository.available.BookRoomRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

class BookRoomViewModel(private val bookDao: BookRoomRepo): ViewModel() {

    fun getBooks() = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(bookDao.getBooks()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    private fun insertItem(bookEntity: List<BookEntity>) {
        viewModelScope.launch {
            bookDao.insertBooks(bookEntity)
        }
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
        val newItem = getNewItemEntry(itemName)
        insertItem(newItem)
    }

    fun isEntryValid(itemName: String): Boolean {
        if (itemName.isBlank()) {
            return false
        }
        return true
    }

}

class BookRoomViewModelFactory(private val bookDao: BookRoomRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookRoomViewModel(bookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}