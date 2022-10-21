package com.lefg095.criptoone.viewmodel;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/lefg095/criptoone/viewmodel/BookViewModel;", "Landroidx/lifecycle/ViewModel;", "booksIBookRepository", "Lcom/lefg095/criptoone/data/interfaces/IBookRepository;", "(Lcom/lefg095/criptoone/data/interfaces/IBookRepository;)V", "_booksResponse", "Landroidx/lifecycle/MutableLiveData;", "Lcom/lefg095/criptoone/domain/stateevent/DataState;", "Lcom/lefg095/criptoone/domain/response/BaseResponse;", "Lcom/lefg095/criptoone/domain/Book;", "bookResponse", "Landroidx/lifecycle/LiveData;", "getBookResponse", "()Landroidx/lifecycle/LiveData;", "makeApiCall", "", "booksStateEvent", "Lcom/lefg095/criptoone/domain/stateevent/BooksStateEvent;", "app_debug"})
public final class BookViewModel extends androidx.lifecycle.ViewModel {
    private final com.lefg095.criptoone.data.interfaces.IBookRepository booksIBookRepository = null;
    private final androidx.lifecycle.MutableLiveData<com.lefg095.criptoone.domain.stateevent.DataState<com.lefg095.criptoone.domain.response.BaseResponse<com.lefg095.criptoone.domain.Book>>> _booksResponse = null;
    
    @javax.inject.Inject()
    public BookViewModel(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.data.interfaces.IBookRepository booksIBookRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.lefg095.criptoone.domain.stateevent.DataState<com.lefg095.criptoone.domain.response.BaseResponse<com.lefg095.criptoone.domain.Book>>> getBookResponse() {
        return null;
    }
    
    public final void makeApiCall(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.domain.stateevent.BooksStateEvent booksStateEvent) {
    }
}