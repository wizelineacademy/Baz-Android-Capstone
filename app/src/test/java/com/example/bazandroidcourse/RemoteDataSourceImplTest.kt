package com.example.bazandroidcourse

import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceImpl
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.RetrofitHelper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Retrofit

class RemoteDataSourceImplTest {
    private lateinit var retorfit:Retrofit
    private lateinit var remoteDataSource:CryptoRemoteDataSourceInterface

    @Before
    fun setup(){
        retorfit = RetrofitHelper.getRetrofitHelper()
        remoteDataSource = CryptoRemoteDataSourceImpl(retorfit)
    }

    @Test
    fun `fetchAllBooks_returnsIsNotNull_test`() {
        runBlocking{
            val data = remoteDataSource.fetchBooks()
            assertNotNull(data)
        }

    }

    @Test
    fun `fetchAllBooks_returnsElemnts_test`() {
        runBlocking{
            val data = remoteDataSource.fetchBooks()
            assert( data.size > 0)
        }
    }
}