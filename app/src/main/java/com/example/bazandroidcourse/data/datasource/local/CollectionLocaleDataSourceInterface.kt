package com.example.bazandroidcourse.data.datasource.local

interface CollectionLocaleDataSourceInterface <T,P>: GeneralLocalDataSourceInterface<T> {
    suspend fun getAll(id:P):List<T>
    suspend fun deleteAll(id: P)
}

