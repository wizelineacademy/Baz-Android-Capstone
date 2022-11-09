package com.example.bazandroidcourse.data.datasource.local

interface RowByIdLocaleDataSourceInterface <T,P>: GeneralLocalDataSourceInterface<T> {
    suspend fun getRow(id:P):T
}