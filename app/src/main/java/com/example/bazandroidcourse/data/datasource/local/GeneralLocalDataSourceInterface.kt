package com.example.bazandroidcourse.data.datasource.local

interface GeneralLocalDataSourceInterface<T> {
     /***
      * Saves a list of a specific type defined by T
      *  @param items:List<T> a list of T type.
      */
     suspend fun saveAll(items: List<T>)

     /***
      * Finds all elements of an datasource
      *  @return List<T> a list of elements of a specific type defined by T
      */
     suspend fun getAll(): List<T>

     /**
      * Allows clean a specific datasource
      */
     suspend fun deleteAll()

     /***
      * Allows add a new row of a specific type(T)
      * @param item:T is a new row to add
      */
     suspend fun addRow(item:T)
}