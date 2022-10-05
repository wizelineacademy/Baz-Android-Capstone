package com.ari.coins.domain.contracts

import com.ari.coins.domain.domainModels.ResultDomain

/**
 * @author Ari Valencia
 * @file SuspendUseCase
 * @description Contract for suspend use cases (need suspend function)
 */

interface SuspendUseCase<P, T> {
    suspend operator fun invoke(params: P): ResultDomain<T>
}
