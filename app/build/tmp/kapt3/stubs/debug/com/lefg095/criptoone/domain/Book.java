package com.lefg095.criptoone.domain;

import java.lang.System;

@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001cH\u00d6\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR\u001e\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e\u00a8\u0006\""}, d2 = {"Lcom/lefg095/criptoone/domain/Book;", "Landroid/os/Parcelable;", "book", "", "minimum_amount", "maximum_amount", "minimum_price", "maximum_price", "minimum_value", "maximum_value", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBook", "()Ljava/lang/String;", "setBook", "(Ljava/lang/String;)V", "getMaximum_amount", "setMaximum_amount", "getMaximum_price", "setMaximum_price", "getMaximum_value", "setMaximum_value", "getMinimum_amount", "setMinimum_amount", "getMinimum_price", "setMinimum_price", "getMinimum_value", "setMinimum_value", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
public class Book implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "book")
    @com.google.gson.annotations.Expose()
    private java.lang.String book;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "minimum_amount")
    @com.google.gson.annotations.Expose()
    private java.lang.String minimum_amount;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "maximum_amount")
    @com.google.gson.annotations.Expose()
    private java.lang.String maximum_amount;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "minimum_price")
    @com.google.gson.annotations.Expose()
    private java.lang.String minimum_price;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "maximum_price")
    @com.google.gson.annotations.Expose()
    private java.lang.String maximum_price;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "minimum_value")
    @com.google.gson.annotations.Expose()
    private java.lang.String minimum_value;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "maximum_value")
    @com.google.gson.annotations.Expose()
    private java.lang.String maximum_value;
    public static final android.os.Parcelable.Creator<com.lefg095.criptoone.domain.Book> CREATOR = null;
    
    public Book() {
        super();
    }
    
    public Book(@org.jetbrains.annotations.NotNull()
    java.lang.String book, @org.jetbrains.annotations.NotNull()
    java.lang.String minimum_amount, @org.jetbrains.annotations.NotNull()
    java.lang.String maximum_amount, @org.jetbrains.annotations.NotNull()
    java.lang.String minimum_price, @org.jetbrains.annotations.NotNull()
    java.lang.String maximum_price, @org.jetbrains.annotations.NotNull()
    java.lang.String minimum_value, @org.jetbrains.annotations.NotNull()
    java.lang.String maximum_value) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBook() {
        return null;
    }
    
    public final void setBook(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMinimum_amount() {
        return null;
    }
    
    public final void setMinimum_amount(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMaximum_amount() {
        return null;
    }
    
    public final void setMaximum_amount(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMinimum_price() {
        return null;
    }
    
    public final void setMinimum_price(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMaximum_price() {
        return null;
    }
    
    public final void setMaximum_price(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMinimum_value() {
        return null;
    }
    
    public final void setMinimum_value(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMaximum_value() {
        return null;
    }
    
    public final void setMaximum_value(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<com.lefg095.criptoone.domain.Book> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.lefg095.criptoone.domain.Book createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.lefg095.criptoone.domain.Book[] newArray(int size) {
            return null;
        }
    }
}