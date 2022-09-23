package com.example.readbitso.repository

import com.example.readbitso.interfaces.BitsoDataSource
import com.example.readbitso.models.bitsoBooks.Books
import com.example.readbitso.models.bitsotickers.Tickers
import javax.inject.Inject

class BitsoRepositoryImp
@Inject constructor(private val retro: BitsoDataSource) : BitsoRepository {
    override suspend fun getBitsoBooks(): Books = retro.getBooks()

}
