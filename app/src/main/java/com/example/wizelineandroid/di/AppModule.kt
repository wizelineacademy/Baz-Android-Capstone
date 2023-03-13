package com.example.wizelineandroid.di

import android.content.Context
import androidx.room.Room
import com.example.wizelineandroid.data.local.BookRoomDataBase
import com.example.wizelineandroid.data.local.dao.BookDao
import com.example.wizelineandroid.data.local.dao.OrderDao
import com.example.wizelineandroid.data.local.dao.TickerDao
import com.example.wizelineandroid.repository.WebService
import com.example.wizelineandroid.repository.available.BookRoomRepo
import com.example.wizelineandroid.repository.available.BookRoomRepoImpl
import com.example.wizelineandroid.repository.available.BooksRepo
import com.example.wizelineandroid.repository.available.BooksRepoImpl
import com.example.wizelineandroid.repository.order.OrderBookRepo
import com.example.wizelineandroid.repository.order.OrderBookRepoImpl
import com.example.wizelineandroid.repository.order.OrderRoomRepo
import com.example.wizelineandroid.repository.order.OrderRoomRepoImpl
import com.example.wizelineandroid.repository.ticker.TickerRepo
import com.example.wizelineandroid.repository.ticker.TickerRepoImpl
import com.example.wizelineandroid.repository.ticker.TickerRoomRepo
import com.example.wizelineandroid.repository.ticker.TickerRoomRepoImpl
import com.example.wizelineandroid.utils.AppConstants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun providesBookRepository(repositoryImpl: BooksRepoImpl): BooksRepo

    @Binds
    abstract fun providesOrderRepository(repositoryOrderImpl: OrderBookRepoImpl): OrderBookRepo

    @Binds
    abstract fun providesTickerRepository(repositoryTickerImpl: TickerRepoImpl): TickerRepo

    @Binds
    abstract fun providesBookRoomRepository(repositoryImplRoom: BookRoomRepoImpl): BookRoomRepo

    @Binds
    abstract fun providesOrderRoomRepository(repositoryImplOrder: OrderRoomRepoImpl): OrderRoomRepo

    @Binds
    abstract fun providesTickerRoomRepository(repositoryImplTicker: TickerRoomRepoImpl): TickerRoomRepo

    companion object {

        @Provides
        fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().also {
                        it.setLevel(
                            HttpLoggingInterceptor.Level.HEADERS
                        )
                    }
                )
                .build()

        @Provides
        fun providesRetrofitInstance(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }

        @Provides
        fun providesCurrencyService(retrofit: Retrofit) = retrofit.create<WebService>()

        @Provides
        fun provideBookRoomDao(appDatabase: BookRoomDataBase): BookDao {
            return appDatabase.bookDao()
        }
        @Provides
        fun provideOrderRoomDao(appDatabase: BookRoomDataBase): OrderDao {
            return appDatabase.orderDao()
        }
        @Provides
        fun provideTickerRoomDao(appDatabase: BookRoomDataBase): TickerDao {
            return appDatabase.tickerDao()
        }

        @Provides
        @Singleton
        fun provideDatabase (@ApplicationContext context: Context) : BookRoomDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                BookRoomDataBase::class.java,
                "books_database"
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries().build()
        }

    }
}
