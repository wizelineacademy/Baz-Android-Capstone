package com.jpgl.cryptocurrencies.domain

import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.data.model.BookModel
import javax.inject.Inject

class GetAvailableBookUseCase @Inject constructor(
    private val cryptoRepository : CryptoRepository
){
    suspend operator fun invoke(): List<BookModel> = cryptoRepository.getAllAvailableBooks()
}