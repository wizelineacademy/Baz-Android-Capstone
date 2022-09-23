package com.example.readbitso.repository

import com.example.readbitso.interfaces.BitsoDataSource
import com.example.readbitso.models.bitsoBooks.Books
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BitsoRepositoryImp
@Inject constructor(private val retro: BitsoDataSource) : BitsoRepository {
    override fun getBitsoBooks(): Observable<Books> = retro.getBooks()

}
