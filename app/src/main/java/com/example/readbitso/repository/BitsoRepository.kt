package com.example.readbitso.repository

import com.example.readbitso.models.bitsotickers.Tickers
import kotlinx.coroutines.flow.Flow

interface BitsoRepository {
    fun getBitsoBooks():Tickers
}

