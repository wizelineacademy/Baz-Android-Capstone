package com.example.readbitso.repository

import com.example.readbitso.models.bitsoBooks.Books

interface BitsoRepository {
   suspend fun getBitsoBooks(): Books
}

