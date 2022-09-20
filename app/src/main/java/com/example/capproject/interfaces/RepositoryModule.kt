package com.example.capproject.interfaces

import com.example.capproject.repository.BitsoRepository
import com.example.capproject.repository.BitsoRepositoryImp
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

