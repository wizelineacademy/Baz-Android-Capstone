package com.capstone.capstonecoins.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.capstone.capstonecoins.data.repository.models.BookDetail
import com.capstone.capstonecoins.domain.api.usecases.DetailCoinUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailCoinViewmodel(private var useCase: DetailCoinUseCase) : ViewModel() {
    val detailCoin = MutableLiveData<BookDetail>()

    fun getDetailCoin(typeCoin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.detailCoin(typeCoin)
            response.collect { detail ->
                detailCoin.postValue(detail)
            }
        }
    }


}

class ViewModelFactorym(private val detailUseCase: DetailCoinUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailCoinViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailCoinViewmodel(detailUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}