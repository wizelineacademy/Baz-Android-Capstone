package com.capstone.capstonecoins.ui.listeners

import com.capstone.capstonecoins.data.models.availablebooks.Payload
import com.capstone.capstonecoins.data.repository.models.Book

interface ListenerAdapter {
    fun listener(book: Book)
}