package com.example.wizelineandroid.presentation.ticker

import androidx.lifecycle.*
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.local.entitys.TickerEntity
import com.example.wizelineandroid.data.remote.model.GetTicker
import com.example.wizelineandroid.repository.ticker.TickerRoomRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TickerRoomViewModel @Inject constructor(private val tickerRoom: TickerRoomRepo) : ViewModel() {

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

    private fun getNewItemEntry(ticker: GetTicker, id: String, fecha: String): TickerEntity {
        return TickerEntity(
            id = id,
            fecha = fecha,
            high = ticker.high,
            last = ticker.last,
            low = ticker.low
        )
    }

    @Singleton
    fun addNewItem(ticker: GetTicker, id: String, fecha: String) {
        val newItem = getNewItemEntry(ticker, id, fecha)
        insertItemTicker(newItem)
    }

    fun isEntryValid(ticker: String): Boolean {
        return ticker.isNotBlank()
    }
}