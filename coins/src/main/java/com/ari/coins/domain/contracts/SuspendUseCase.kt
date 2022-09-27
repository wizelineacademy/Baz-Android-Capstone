package com.ari.coins.domain.contracts

import com.ari.coins.domain.domainModels.ResultDomain

/**
 * Contract for suspend use cases
 */
interface SuspendUseCase<P, T> {
    suspend operator fun invoke(params: P): ResultDomain<T>
}