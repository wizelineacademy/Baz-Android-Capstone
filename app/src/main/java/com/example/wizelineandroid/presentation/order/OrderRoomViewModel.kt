package com.example.wizelineandroid.presentation.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.local.entitys.AskEntity
import com.example.wizelineandroid.data.local.entitys.BidsEntity
import com.example.wizelineandroid.data.remote.model.Ask
import com.example.wizelineandroid.data.remote.model.Bids
import com.example.wizelineandroid.repository.order.OrderRoomRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

class OrderRoomViewModel(private val orderDao: OrderRoomRepo): ViewModel() {

    fun getAsk(id: String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(orderDao.getAsk(id)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun getBids(id: String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(orderDao.getBids(id)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    private fun insertAsk(orderEntity: List<AskEntity>) {
        viewModelScope.launch {
            orderDao.insertAsk(orderEntity)
        }
    }

    private fun insertBids(orderEntity: List<BidsEntity>) {
        viewModelScope.launch {
            orderDao.insertBids(orderEntity)
        }
    }

    private fun getNewAskEntry(itemName: List<Ask>): List<AskEntity> {
        val entities = itemName.map { currency ->
            AskEntity(
                id = currency.book,
                book = currency.book,
                amount = currency.amount,
                price = currency.price
            )
        }
        return entities
    }

    private fun getNewBidsEntry(itemName: List<Bids>): List<BidsEntity> {
        val entities = itemName.map { currency ->
            BidsEntity(
                id = currency.book,
                book = currency.book,
                amount = currency.amount,
                price = currency.price
            )
        }
        return entities
    }

    @Singleton
    fun addNewItemAsk(itemName: List<Ask>) {
        val newItem = getNewAskEntry(itemName)
        insertAsk(newItem)
    }

    @Singleton
    fun addNewItemBids(itemName: List<Bids>) {
        val newItem = getNewBidsEntry(itemName)
        insertBids(newItem)
    }

    fun isEntryValidAsk(itemName: String): Boolean {
        if (itemName.isBlank()) {
            return false
        }
        return true
    }

}

class AskRoomViewModelFactory(private val bookDao: OrderRoomRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrderRoomViewModel(bookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}