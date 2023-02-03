package com.baz.cmv.criptomonedas.coins.data.remote.network

open class ResponseStatus <T> {
    class Succes <T> (val payload: T) : ResponseStatus <T>()
    class Loading <T> : ResponseStatus <T>()
    class Error <T> (val errorId: String) : ResponseStatus <T>()


}