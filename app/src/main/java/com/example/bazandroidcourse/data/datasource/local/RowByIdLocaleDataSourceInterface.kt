package com.example.bazandroidcourse.data.datasource.local

interface RowByIdLocaleDataSourceInterface<T, P> : GeneralLocalDataSourceInterface<T> {
    /***
     * Finds an element by id of (P) type
     * @param id is the id of P type
     * @return an object object of a specific type defined by T
     */
    suspend fun getRow(id: P): T
}