package com.lefg095.criptoone.domain.response;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0004H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u00c6\u0003J)\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0004H\u00d6\u0001R$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0019"}, d2 = {"Lcom/lefg095/criptoone/domain/response/BaseResponse;", "T", "", "success", "", "payload", "", "(Ljava/lang/String;Ljava/util/List;)V", "getPayload", "()Ljava/util/List;", "setPayload", "(Ljava/util/List;)V", "getSuccess", "()Ljava/lang/String;", "setSuccess", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class BaseResponse<T extends java.lang.Object> {
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "success")
    @com.google.gson.annotations.Expose()
    private java.lang.String success;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "payload")
    @com.google.gson.annotations.Expose()
    private java.util.List<? extends T> payload;
    
    @org.jetbrains.annotations.NotNull()
    public final com.lefg095.criptoone.domain.response.BaseResponse<T> copy(@org.jetbrains.annotations.NotNull()
    java.lang.String success, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> payload) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public BaseResponse() {
        super();
    }
    
    public BaseResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String success, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> payload) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSuccess() {
        return null;
    }
    
    public final void setSuccess(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<T> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<T> getPayload() {
        return null;
    }
    
    public final void setPayload(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> p0) {
    }
}