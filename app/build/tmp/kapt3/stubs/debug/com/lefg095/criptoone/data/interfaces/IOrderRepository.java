package com.lefg095.criptoone.data.interfaces;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/lefg095/criptoone/data/interfaces/IOrderRepository;", "", "getOrder", "Lkotlinx/coroutines/flow/Flow;", "Lcom/lefg095/criptoone/domain/stateevent/DataState;", "Lcom/lefg095/criptoone/domain/response/OrderResponse;", "nameBook", "", "app_debug"})
public abstract interface IOrderRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.lefg095.criptoone.domain.stateevent.DataState<com.lefg095.criptoone.domain.response.OrderResponse>> getOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String nameBook);
}