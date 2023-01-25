package com.javg.cryptocurrencies.data.viewmodel

import androidx.lifecycle.ViewModel
import com.javg.cryptocurrencies.data.repository.CRYCryptocurrenciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CRYHomeVM @Inject constructor(private val repository: CRYCryptocurrenciesRepository): ViewModel() {
}