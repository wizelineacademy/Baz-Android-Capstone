package com.wizeline.criptocurrency.data.database.di

import android.content.Context
import androidx.room.Room
import com.wizeline.criptocurrency.data.database.CriptoCurrencyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CriptoCurrencyDB::class.java, "criptoCurrencyDB")
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideCryptoCurrencyDao(db: CriptoCurrencyDB) = db.getCriptoCurrencyDao()

}