package com.lefg095.criptoone.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u001a\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0010\u0010)\u001a\u00020\'2\u0006\u0010*\u001a\u00020+H\u0002J\u0016\u0010,\u001a\u00020\'2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020.J\b\u0010/\u001a\u00020\'H\u0002J\b\u00100\u001a\u00020\'H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u0018\u001a\u0004\b\u001b\u0010\u001c\u00a8\u00061"}, d2 = {"Lcom/lefg095/criptoone/ui/DetailFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapterAsk", "Lcom/lefg095/criptoone/ui/adapters/AskAdapter;", "adapterBids", "Lcom/lefg095/criptoone/ui/adapters/BidAdapter;", "binding", "Lcom/lefg095/criptoone/databinding/FragmentDetailBinding;", "getBinding", "()Lcom/lefg095/criptoone/databinding/FragmentDetailBinding;", "setBinding", "(Lcom/lefg095/criptoone/databinding/FragmentDetailBinding;)V", "book", "Lcom/lefg095/criptoone/domain/Book;", "getBook", "()Lcom/lefg095/criptoone/domain/Book;", "setBook", "(Lcom/lefg095/criptoone/domain/Book;)V", "orderViewModel", "Lcom/lefg095/criptoone/viewmodel/OrderViewModel;", "getOrderViewModel", "()Lcom/lefg095/criptoone/viewmodel/OrderViewModel;", "orderViewModel$delegate", "Lkotlin/Lazy;", "tickerviewModel", "Lcom/lefg095/criptoone/viewmodel/TickerViewModel;", "getTickerviewModel", "()Lcom/lefg095/criptoone/viewmodel/TickerViewModel;", "tickerviewModel$delegate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "printBidsAsks", "order", "Lcom/lefg095/criptoone/domain/Order;", "printDataScreen", "ticker", "Lcom/lefg095/criptoone/domain/Ticker;", "subscribeOrderObservers", "subscribeTickerObservers", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class DetailFragment extends androidx.fragment.app.Fragment {
    public com.lefg095.criptoone.databinding.FragmentDetailBinding binding;
    private final kotlin.Lazy tickerviewModel$delegate = null;
    private final kotlin.Lazy orderViewModel$delegate = null;
    private com.lefg095.criptoone.ui.adapters.AskAdapter adapterAsk;
    private com.lefg095.criptoone.ui.adapters.BidAdapter adapterBids;
    @org.jetbrains.annotations.Nullable()
    private com.lefg095.criptoone.domain.Book book;
    
    public DetailFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lefg095.criptoone.databinding.FragmentDetailBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.databinding.FragmentDetailBinding p0) {
    }
    
    private final com.lefg095.criptoone.viewmodel.TickerViewModel getTickerviewModel() {
        return null;
    }
    
    private final com.lefg095.criptoone.viewmodel.OrderViewModel getOrderViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lefg095.criptoone.domain.Book getBook() {
        return null;
    }
    
    public final void setBook(@org.jetbrains.annotations.Nullable()
    com.lefg095.criptoone.domain.Book p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void subscribeOrderObservers() {
    }
    
    private final void subscribeTickerObservers() {
    }
    
    public final void printDataScreen(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.domain.Book book, @org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.domain.Ticker ticker) {
    }
    
    private final void printBidsAsks(com.lefg095.criptoone.domain.Order order) {
    }
}