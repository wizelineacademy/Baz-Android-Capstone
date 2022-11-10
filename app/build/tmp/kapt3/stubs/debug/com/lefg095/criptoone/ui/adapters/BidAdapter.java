package com.lefg095.criptoone.ui.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0014"}, d2 = {"Lcom/lefg095/criptoone/ui/adapters/BidAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/lefg095/criptoone/ui/adapters/BidAdapter$BidViewHolder;", "itemList", "", "Lcom/lefg095/criptoone/domain/Bid;", "(Ljava/util/List;)V", "getItemList", "()Ljava/util/List;", "getItemCount", "", "onBindViewHolder", "", "holder", "i", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BidViewHolder", "app_debug"})
public final class BidAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.lefg095.criptoone.ui.adapters.BidAdapter.BidViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.lefg095.criptoone.domain.Bid> itemList = null;
    
    public BidAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<com.lefg095.criptoone.domain.Bid> itemList) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.lefg095.criptoone.domain.Bid> getItemList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.lefg095.criptoone.ui.adapters.BidAdapter.BidViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.lefg095.criptoone.ui.adapters.BidAdapter.BidViewHolder holder, int i) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0010"}, d2 = {"Lcom/lefg095/criptoone/ui/adapters/BidAdapter$BidViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "binding", "Lcom/lefg095/criptoone/databinding/ItemOrderBinding;", "numberFormat", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "getNumberFormat", "()Ljava/text/NumberFormat;", "bind", "", "bid", "Lcom/lefg095/criptoone/domain/Bid;", "app_debug"})
    public static final class BidViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.lefg095.criptoone.databinding.ItemOrderBinding binding = null;
        private final java.text.NumberFormat numberFormat = null;
        
        public BidViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        public final java.text.NumberFormat getNumberFormat() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.lefg095.criptoone.domain.Bid bid) {
        }
    }
}