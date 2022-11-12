package com.example.bazandroidcourse.data.datasource.local

interface CollectionLocaleDataSourceInterface <T,P>: GeneralLocalDataSourceInterface<T> {
    /***
     * Allows gets a list of a specific type defined by T filtered by P type
     *  @param id:P id to filter rows
     *  @return a list of T type.
     */
    suspend fun getAll(id:P):List<T>

    /***
     * Allows delete all rows filtered by P type
     *  @param id:P id to filter rows to delete
     */
    suspend fun deleteAll(id: P)
}

