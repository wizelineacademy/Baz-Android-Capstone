package com.example.myapplication.useCases

import com.example.myapplication.model.CriptoCurrency
import com.example.myapplication.model.Payload
import com.example.myapplication.model.asExternalModel
import com.example.myapplication.repository.BitsoRepository
import javax.inject.Inject

/**
 * Created by: Juan Antonio Amado
 * date: 23,septiembre,2022
 */
class LoadCriptoWithFilterCurrencyUseCase @Inject constructor(private val repository: BitsoRepository) {

    suspend operator fun invoke(): List<CriptoCurrency> =
        repository.loadCripto().payload.map(Payload::asExternalModel).filter {
            it.name.contains("mxn")
        }

}