package com.example.readbitso.repository

import com.example.readbitso.models.bitsoBooks.Books
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

interface BitsoRepository {
    fun getBitsoBooks(): Observable<Books>
}

