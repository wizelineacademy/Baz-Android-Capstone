package com.example.wizelineandroid.presentation

import androidx.lifecycle.*
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.local.dao.BookDao
import com.example.wizelineandroid.data.local.dao.TickerDao
import com.example.wizelineandroid.data.local.entitys.TickerEntity
import com.example.wizelineandroid.data.remote.model.GetTicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

class TickerRoomViewModel(private val tickerDao: TickerDao): ViewModel() {

    //val allTicker: LiveData<TickerEntity> = bookDao.getTicket().asLiveData()

    fun allTicker(id: String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(tickerDao.getTicket(id)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    private fun insertItemTicker(tickerEntity: TickerEntity) {
        viewModelScope.launch {
            tickerDao.insertTicker(tickerEntity)
        }
    }

    private fun getNewItemEntry(ticker: GetTicker,id: String): TickerEntity {
        return TickerEntity(
                id = id,
                high = ticker.high,
                last = ticker.last,
                low = ticker.low
            )
    }

    @Singleton
    fun addNewItem(ticker: GetTicker, id: String) {
        val newItem = getNewItemEntry(ticker,id)
        insertItemTicker(newItem)
    }

    fun isEntryValid(ticker: String): Boolean {
        if (ticker.isBlank()) {
            return false
        }
        return true
    }

}

class TickerRoomViewModelFactory(private val bookDao: TickerDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TickerRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TickerRoomViewModel(bookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}