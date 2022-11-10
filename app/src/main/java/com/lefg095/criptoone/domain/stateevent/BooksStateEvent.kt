package com.lefg095.criptoone.domain.stateevent

sealed class BooksStateEvent {
    object GetBooks : BooksStateEvent()
}