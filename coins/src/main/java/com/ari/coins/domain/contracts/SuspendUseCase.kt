package com.ari.coins.domain.contracts

import com.ari.coins.data.models.ResultData

/**
 * Contract for suspend use cases
 */
interface SuspendUseCase<P, T> {
    suspend operator fun invoke(params: P): ResultData<T>
}