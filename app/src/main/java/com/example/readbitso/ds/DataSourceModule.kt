package com.example.readbitso.ds

import android.content.Context
import androidx.room.Room
import com.example.readbitso.ds.room.dao.AskBidsDao
import com.example.readbitso.ds.room.dao.CriptocurrenciesDao
import com.example.readbitso.ds.room.dao.TradesDao
import com.example.readbitso.ds.room.dao.Db.AppDatabase
import com.example.readbitso.repository.datastore.DataStoreRepository
import com.example.readbitso.repository.datastore.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    @Named("Baseurl")
    fun getUri()="https://api.bitso.com/v3/"

    @Singleton
    @Provides
    fun getRetrofit(@Named("Baseurl") baseurl:String):Retrofit
    {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(baseurl)
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun restBinanceDataSource(retrofit: Retrofit): BitsoDataSource =
        retrofit.create(BitsoDataSource::class.java)

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(app)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "user_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun userdao(db: AppDatabase): CriptocurrenciesDao =db.tokensDao()

    @Provides
    @Singleton
    fun transactionsdao(db: AppDatabase): TradesDao =db.tokensDao1()

    @Provides
    @Singleton
    fun askbidsdao(db: AppDatabase): AskBidsDao =db.tokensDao2()


}





