package com.example.bazandroidcourse.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object DispatchersModule {
    @Singleton
    @Provides
    fun providesCoroutineScope(
        defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

//    @Provides
//    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
//
//    @Provides
//    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

//@Retention(AnnotationRetention.RUNTIME)
//@Qualifier
//annotation class DefaultDispatcher