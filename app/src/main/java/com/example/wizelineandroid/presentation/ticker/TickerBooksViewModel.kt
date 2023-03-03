package com.example.wizelineandroid.presentation.ticker

import androidx.lifecycle.*
import com.example.wizelineandroid.core.Resource
import com.example.wizelineandroid.data.remote.model.GetTickers
import com.example.wizelineandroid.repository.ticker.TickerRepo
import kotlinx.coroutines.Dispatchers

class TickerBooksViewModel(private val repo: TickerRepo) : ViewModel() {

    private val _detail_books = MutableLiveData<GetTickers>()
    val detailBooks = _detail_books

    fun fetchTickersBooks(id: String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getTickerBooks(id)))
            _detail_books.postValue(repo.getTickerBooks(id))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class TickerViewModelFactory(private val repo: TickerRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TickerRepo::class.java).newInstance(repo)
    }
}
