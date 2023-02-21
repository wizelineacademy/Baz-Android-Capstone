package com.example.baz_android_capstone.util // ktlint-disable package-name

import kotlinx.coroutines.flow.* // ktlint-disable no-wildcard-imports

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map {
                Resource.Success(it)
            }
        } catch (throwable: Throwable) {
            query().map {
                Resource.Error(throwable = throwable, data = it)
            }
        }
    } else {
        query().map {
            Resource.Success(it)
        }
    }

    emitAll(flow)
}
