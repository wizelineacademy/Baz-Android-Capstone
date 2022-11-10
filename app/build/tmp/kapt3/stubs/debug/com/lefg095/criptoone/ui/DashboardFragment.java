package com.lefg095.criptoone.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0016\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0012H\u0016J\b\u0010\u001f\u001a\u00020\u000fH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006 "}, d2 = {"Lcom/lefg095/criptoone/ui/DashboardFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/lefg095/criptoone/ui/callbacks/ItemBookCallBack;", "()V", "adapterBooks", "Lcom/lefg095/criptoone/ui/adapters/BooksAdapter;", "binding", "Lcom/lefg095/criptoone/databinding/FragmentDashboardBinding;", "bookViewModel", "Lcom/lefg095/criptoone/viewmodel/BookViewModel;", "getBookViewModel", "()Lcom/lefg095/criptoone/viewmodel/BookViewModel;", "bookViewModel$delegate", "Lkotlin/Lazy;", "initRecyclerView", "", "books", "", "Lcom/lefg095/criptoone/domain/Book;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "showDetailClicket", "book", "subscribeObservers", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class DashboardFragment extends androidx.fragment.app.Fragment implements com.lefg095.criptoone.ui.callbacks.ItemBookCallBack {
    private com.lefg095.criptoone.databinding.FragmentDashboardBinding binding;
    private final kotlin.Lazy bookViewModel$delegate = null;
    private com.lefg095.criptoone.ui.adapters.BooksAdapter adapterBooks;
    
    public DashboardFragment() {
        super();
    }
    
    private final com.lefg095.criptoone.viewmodel.BookViewModel getBookViewModel() {
        return null;
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
    
    private final void subscribeObservers() {
    }
    
    private final void initRecyclerView(java.util.List<? extends com.lefg095.criptoone.domain.Book> books) {
    }
    
    @java.lang.Override()
    public void showDetailClicket(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.domain.Book book) {
    }
}