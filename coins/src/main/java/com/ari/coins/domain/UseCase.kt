package com.ari.coins.domain

import com.ari.coins.data.models.Result

interface UseCase<Params, T> {
    suspend operator fun invoke(params: Params): Result<T>
}