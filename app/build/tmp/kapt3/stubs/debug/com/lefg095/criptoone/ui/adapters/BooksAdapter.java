package com.lefg095.criptoone.ui.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lcom/lefg095/criptoone/ui/adapters/BooksAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/lefg095/criptoone/ui/adapters/BooksAdapter$BooksViewHolder;", "books", "", "Lcom/lefg095/criptoone/domain/Book;", "mCallback", "Lcom/lefg095/criptoone/ui/callbacks/ItemBookCallBack;", "(Ljava/util/List;Lcom/lefg095/criptoone/ui/callbacks/ItemBookCallBack;)V", "getBooks", "()Ljava/util/List;", "getMCallback", "()Lcom/lefg095/criptoone/ui/callbacks/ItemBookCallBack;", "getItemCount", "", "onBindViewHolder", "", "holder", "i", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BooksViewHolder", "app_debug"})
public final class BooksAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.lefg095.criptoone.ui.adapters.BooksAdapter.BooksViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.lefg095.criptoone.domain.Book> books = null;
    @org.jetbrains.annotations.NotNull()
    private final com.lefg095.criptoone.ui.callbacks.ItemBookCallBack mCallback = null;
    
    public BooksAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.lefg095.criptoone.domain.Book> books, @org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.ui.callbacks.ItemBookCallBack mCallback) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.lefg095.criptoone.domain.Book> getBooks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lefg095.criptoone.ui.callbacks.ItemBookCallBack getMCallback() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.lefg095.criptoone.ui.adapters.BooksAdapter.BooksViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.ui.adapters.BooksAdapter.BooksViewHolder holder, int i) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/lefg095/criptoone/ui/adapters/BooksAdapter$BooksViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "binding", "Lcom/lefg095/criptoone/databinding/ItemBookBinding;", "bind", "", "book", "Lcom/lefg095/criptoone/domain/Book;", "mCallback", "Lcom/lefg095/criptoone/ui/callbacks/ItemBookCallBack;", "app_debug"})
    public static final class BooksViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.lefg095.criptoone.databinding.ItemBookBinding binding = null;
        
        public BooksViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.lefg095.criptoone.domain.Book book, @org.jetbrains.annotations.NotNull()
        com.lefg095.criptoone.ui.callbacks.ItemBookCallBack mCallback) {
        }
    }
}