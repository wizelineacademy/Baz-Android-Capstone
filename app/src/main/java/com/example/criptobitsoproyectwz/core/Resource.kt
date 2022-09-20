package com.example.criptobitsoproyectwz.core

import java.lang.Exception


/***
 * reusar clase de tipo generico
 */
sealed class Resource<out T> {
    class Loading<out T>: Resource<T>()
    data class Succes<out T> (val data: T): Resource<T>()
    data class Failure(val exception: Exception): Resource<Nothing>()
}