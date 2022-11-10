package com.lefg095.criptoone.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u001b\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/lefg095/criptoone/di/ApiService;", "", "getBooks", "Lcom/lefg095/criptoone/domain/response/BaseResponse;", "Lcom/lefg095/criptoone/domain/Book;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrders", "Lcom/lefg095/criptoone/domain/response/OrderResponse;", "nameBook", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTicker", "Lcom/lefg095/criptoone/domain/response/TickerResponse;", "app_debug"})
public abstract interface ApiService {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "available_books/")
    public abstract java.lang.Object getBooks(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lefg095.criptoone.domain.response.BaseResponse<com.lefg095.criptoone.domain.Book>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "ticker/")
    public abstract java.lang.Object getTicker(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "book")
    java.lang.String nameBook, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lefg095.criptoone.domain.response.TickerResponse> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "order_book/")
    public abstract java.lang.Object getOrders(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "book")
    java.lang.String nameBook, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.lefg095.criptoone.domain.response.OrderResponse> continuation);
}