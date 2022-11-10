package com.lefg095.criptoone.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/lefg095/criptoone/data/OrderRepository;", "Lcom/lefg095/criptoone/data/interfaces/IOrderRepository;", "apiService", "Lcom/lefg095/criptoone/di/ApiService;", "(Lcom/lefg095/criptoone/di/ApiService;)V", "getOrder", "Lkotlinx/coroutines/flow/Flow;", "Lcom/lefg095/criptoone/domain/stateevent/DataState;", "Lcom/lefg095/criptoone/domain/response/OrderResponse;", "nameBook", "", "app_debug"})
public final class OrderRepository implements com.lefg095.criptoone.data.interfaces.IOrderRepository {
    private final com.lefg095.criptoone.di.ApiService apiService = null;
    
    public OrderRepository(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.di.ApiService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<com.lefg095.criptoone.domain.stateevent.DataState<com.lefg095.criptoone.domain.response.OrderResponse>> getOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String nameBook) {
        return null;
    }
}