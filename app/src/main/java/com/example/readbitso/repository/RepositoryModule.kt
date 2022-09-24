package com.example.readbitso.repository

import com.example.readbitso.repository.BitsoRepository
import com.example.readbitso.repository.BitsoRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bitsoRepo (repo: BitsoRepositoryImp): BitsoRepository

}

