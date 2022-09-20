package com.example.capstoneproject.data.network

import com.example.capstoneproject.core.RetrofitHelper
import com.example.capstoneproject.data.model.AvailableBookModel
import com.example.capstoneproject.data.model.BitsoProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BitsoService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAvailableBooks():List<AvailableBookModel>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(BitsoApiClient::class.java).getAllAvailableBooks()
            BitsoProvider.availableBooks = response.body()?.payload ?: emptyList()
            response.body()?.payload ?: emptyList()
        }
    }
}