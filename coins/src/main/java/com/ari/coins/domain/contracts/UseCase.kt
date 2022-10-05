package com.ari.coins.domain.contracts

/**
 * @author Ari Valencia
 * @file UseCase
 * @description Contract for simple use cases (no need suspend function)
 */

interface UseCase<P, T> {
    operator fun invoke(params: P): T
}
