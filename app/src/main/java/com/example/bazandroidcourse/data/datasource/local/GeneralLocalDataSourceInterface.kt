package com.example.bazandroidcourse.data.datasource.local

/***
 *  This interface was designed like as the most general datasource interface to provides
 *  implementers with the following behaviors:
 * - store several elements
 * - get all elements of a type
 * - clear a table by deleting all rows
 * - add a row of a specific type
 */
interface GeneralLocalDataSourceInterface<T> {
     /***
      * Saves a list of a specific type defined by T
      *  @param items a list of T type.
      */
     suspend fun saveAll(items:List<T>)
     /***
      * Finds al elements of an datasource
      *  @return List<T> a list of elements of a specific type defined by T
      */
     suspend fun getAll():List<T>
     suspend fun deleteAll()
     suspend fun addRow(item:T)
}