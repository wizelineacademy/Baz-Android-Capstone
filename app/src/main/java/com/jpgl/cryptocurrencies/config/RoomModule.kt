package com.jpgl.cryptocurrencies.config


import android.content.Context
import androidx.room.Room
import com.jpgl.cryptocurrencies.data.database.CryptoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private val CRYPTO_DATABASE_NAME = "cryptodatabase.db"
    lateinit var aplicationInstance: InitialApplication
    private var databaseInstance: CryptoDatabase? = null

    @Singleton
    @Provides
    fun provideRoom(): CryptoDatabase {
        aplicationInstance.let {
            if(databaseInstance == null){
                synchronized(CryptoDatabase::class.java) {
                    databaseInstance = Room.databaseBuilder(it, CryptoDatabase::class.java, CRYPTO_DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }

        return databaseInstance!!
    }

    @Singleton
    @Provides
    fun provideBookDao() = provideRoom().getBookDao()

    @Singleton
    @Provides
    fun provideBidsDao() = provideRoom().getBidsDao()

    @Singleton
    @Provides
    fun provideAsksDao() = provideRoom().getAsksDao()

    @Singleton
    @Provides
    fun provideTickerDao() = provideRoom().getTickerDao()

}