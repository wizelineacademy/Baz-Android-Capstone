package com.ari.coins.domain

import com.ari.coins.data.models.Result

/**
 * Contract for use cases
 */
interface UseCase<P, T> {
    suspend operator fun invoke(params: P): Result<T>
}