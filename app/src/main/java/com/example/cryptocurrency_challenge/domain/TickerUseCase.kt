package com.example.cryptocurrency_challenge.domain

import com.example.cryptocurrency_challenge.data.model.Payload_Ticker
import com.example.cryptocurrency_challenge.repository.TickerRepository
import javax.inject.Inject

class TickerUseCase @Inject constructor (private val repository: TickerRepository) {
    suspend operator fun invoke(currency_name: String?): Payload_Ticker =
        repository.get_Ticker(currency_name)
}