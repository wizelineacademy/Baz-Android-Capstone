package com.lefg095.criptoone.viewmodel;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/lefg095/criptoone/viewmodel/TickerViewModel;", "Landroidx/lifecycle/ViewModel;", "tickerIRepository", "Lcom/lefg095/criptoone/data/interfaces/ITickerRepository;", "(Lcom/lefg095/criptoone/data/interfaces/ITickerRepository;)V", "_tickerResponse", "Landroidx/lifecycle/MutableLiveData;", "Lcom/lefg095/criptoone/domain/stateevent/DataState;", "Lcom/lefg095/criptoone/domain/response/TickerResponse;", "tickerResponse", "Landroidx/lifecycle/LiveData;", "getTickerResponse", "()Landroidx/lifecycle/LiveData;", "makeApiCall", "", "tickerStateEvent", "Lcom/lefg095/criptoone/domain/stateevent/TickerStateEvent;", "app_debug"})
public final class TickerViewModel extends androidx.lifecycle.ViewModel {
    private final com.lefg095.criptoone.data.interfaces.ITickerRepository tickerIRepository = null;
    private final androidx.lifecycle.MutableLiveData<com.lefg095.criptoone.domain.stateevent.DataState<com.lefg095.criptoone.domain.response.TickerResponse>> _tickerResponse = null;
    
    @javax.inject.Inject()
    public TickerViewModel(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.data.interfaces.ITickerRepository tickerIRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.lefg095.criptoone.domain.stateevent.DataState<com.lefg095.criptoone.domain.response.TickerResponse>> getTickerResponse() {
        return null;
    }
    
    public final void makeApiCall(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.domain.stateevent.TickerStateEvent tickerStateEvent) {
    }
}