package com.example.bazandroidcourse

import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceImpl
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.apiInstance
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.retrofitInstance
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
        retorfit = retrofitInstance
        remoteDataSource = CryptoRemoteDataSourceImpl(apiInstance)
    }

}