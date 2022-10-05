package com.ari.coins.domain.domainModels

sealed class ResultDomain<T> {
    data class Error<T>(val message: String, val code: Int) : ResultDomain<T>()
    data class Success<T>(val data: T) : ResultDomain<T>()
}
