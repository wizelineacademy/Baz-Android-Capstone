package com.lefg095.criptoone.di;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/lefg095/criptoone/di/RepositoryModule;", "", "()V", "provideBookRepository", "Lcom/lefg095/criptoone/data/interfaces/IBookRepository;", "apiService", "Lcom/lefg095/criptoone/di/ApiService;", "provideOrderRepository", "Lcom/lefg095/criptoone/data/interfaces/IOrderRepository;", "provideTickerRepository", "Lcom/lefg095/criptoone/data/interfaces/ITickerRepository;", "app_debug"})
@dagger.Module()
public final class RepositoryModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.lefg095.criptoone.di.RepositoryModule INSTANCE = null;
    
    private RepositoryModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final com.lefg095.criptoone.data.interfaces.IBookRepository provideBookRepository(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.di.ApiService apiService) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final com.lefg095.criptoone.data.interfaces.ITickerRepository provideTickerRepository(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.di.ApiService apiService) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final com.lefg095.criptoone.data.interfaces.IOrderRepository provideOrderRepository(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.di.ApiService apiService) {
        return null;
    }
}