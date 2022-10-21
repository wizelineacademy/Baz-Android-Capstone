package com.lefg095.criptoone.domain.response;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/lefg095/criptoone/domain/response/OrderResponse;", "", "success", "", "payload", "Lcom/lefg095/criptoone/domain/Order;", "(Ljava/lang/String;Lcom/lefg095/criptoone/domain/Order;)V", "getPayload", "()Lcom/lefg095/criptoone/domain/Order;", "setPayload", "(Lcom/lefg095/criptoone/domain/Order;)V", "getSuccess", "()Ljava/lang/String;", "setSuccess", "(Ljava/lang/String;)V", "app_debug"})
public final class OrderResponse {
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "success")
    @com.google.gson.annotations.Expose()
    private java.lang.String success;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "payload")
    @com.google.gson.annotations.Expose()
    private com.lefg095.criptoone.domain.Order payload;
    
    public OrderResponse() {
        super();
    }
    
    public OrderResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String success, @org.jetbrains.annotations.Nullable()
    com.lefg095.criptoone.domain.Order payload) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSuccess() {
        return null;
    }
    
    public final void setSuccess(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lefg095.criptoone.domain.Order getPayload() {
        return null;
    }
    
    public final void setPayload(@org.jetbrains.annotations.Nullable()
    com.lefg095.criptoone.domain.Order p0) {
    }
}