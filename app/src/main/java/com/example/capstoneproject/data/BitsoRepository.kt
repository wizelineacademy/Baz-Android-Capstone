package com.example.capstoneproject.data

import com.example.capstoneproject.data.model.AvailableBookModel
import com.example.capstoneproject.data.model.BitsoProvider
import com.example.capstoneproject.data.network.BitsoService

class BitsoRepository {

    private val api = BitsoService()

    suspend fun getAllAvailableBooks(): List<AvailableBookModel> {
        val response = api.getAvailableBooks()
        BitsoProvider.availableBooks = response
        return response
    }
}