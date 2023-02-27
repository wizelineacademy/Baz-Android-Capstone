package com.example.wizelineandroid.presentation

import androidx.lifecycle.*
import com.example.wizelineandroid.data.local.dao.BookDao
import com.example.wizelineandroid.data.local.entitys.BookEntity
import com.example.wizelineandroid.data.remote.model.ModelBook
import kotlinx.coroutines.launch
import javax.inject.Singleton

class BookRoomViewModel(private val bookDao: BookDao): ViewModel() {

    val allBooks: LiveData<List<BookEntity>> = bookDao.getBooks().asLiveData()

    private fun insertItem(bookEntity: List<BookEntity>) {
        viewModelScope.launch {
            bookDao.insert(bookEntity)
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

class BookRoomViewModelFactory(private val bookDao: BookDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookRoomViewModel(bookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}