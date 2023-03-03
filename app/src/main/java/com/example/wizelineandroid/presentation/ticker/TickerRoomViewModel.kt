package com.example.wizelineandroid.presentation.ticker

import androidx.lifecycle.*
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.local.entitys.TickerEntity
import com.example.wizelineandroid.data.remote.model.GetTicker
import com.example.wizelineandroid.repository.ticker.TickerRoomRepo
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TickerRoomViewModel(private val tickerRoom: TickerRoomRepo) : ViewModel() {

    private val _detail_books = MutableLiveData<TickerEntity>()
    val detailBooks = _detail_books

    fun allTicker(id: String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(tickerRoom.getTicker(id)))
            _detail_books.postValue(tickerRoom.getTicker(id))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    private fun insertItemTicker(tickerEntity: TickerEntity) {
        viewModelScope.launch { tickerRoom.insertTicket(tickerEntity) }
    }

    private fun getNewItemEntry(ticker: GetTicker, id: String): TickerEntity {
        return TickerEntity(
            id = id,
            high = ticker.high,
            last = ticker.last,
            low = ticker.low
        )
    }

    @Singleton
    fun addNewItem(ticker: GetTicker, id: String) {
        val newItem = getNewItemEntry(ticker, id)
        insertItemTicker(newItem)
    }

    fun isEntryValid(ticker: String): Boolean {
        return ticker.isNotBlank()
    }
}

class TickerRoomViewModelFactory(private val tickerRoom: TickerRoomRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TickerRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TickerRoomViewModel(tickerRoom) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}