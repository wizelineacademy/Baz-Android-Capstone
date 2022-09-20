package com.ari.coins.domain.contracts

/**
 * Contract for simple use cases
 */
interface UseCase<P, T> {
    operator fun invoke(params: P): T
}