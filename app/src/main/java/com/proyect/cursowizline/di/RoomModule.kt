package com.proyect.cursowizline.di

import android.content.Context
import androidx.room.Room
import com.proyect.cursowizline.database.CryptoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val CRYPTO_DATABASE_NAME = "crypto_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)=
        Room.databaseBuilder(context, CryptoDatabase::class.java, CRYPTO_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideCryptoDao(db: CryptoDatabase)= db.getCryptoDao()
}