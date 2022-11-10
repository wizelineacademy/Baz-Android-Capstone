package com.lefg095.criptoone.domain.stateevent;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/lefg095/criptoone/domain/stateevent/TickerStateEvent;", "", "()V", "GetTicker", "None", "Lcom/lefg095/criptoone/domain/stateevent/TickerStateEvent$GetTicker;", "Lcom/lefg095/criptoone/domain/stateevent/TickerStateEvent$None;", "app_debug"})
public abstract class TickerStateEvent {
    
    private TickerStateEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/lefg095/criptoone/domain/stateevent/TickerStateEvent$GetTicker;", "Lcom/lefg095/criptoone/domain/stateevent/TickerStateEvent;", "nameBook", "", "(Ljava/lang/String;)V", "getNameBook", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class GetTicker extends com.lefg095.criptoone.domain.stateevent.TickerStateEvent {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String nameBook = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.lefg095.criptoone.domain.stateevent.TickerStateEvent.GetTicker copy(@org.jetbrains.annotations.NotNull()
        java.lang.String nameBook) {
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
        
        public GetTicker(@org.jetbrains.annotations.NotNull()
        java.lang.String nameBook) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getNameBook() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/lefg095/criptoone/domain/stateevent/TickerStateEvent$None;", "Lcom/lefg095/criptoone/domain/stateevent/TickerStateEvent;", "()V", "app_debug"})
    public static final class None extends com.lefg095.criptoone.domain.stateevent.TickerStateEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.lefg095.criptoone.domain.stateevent.TickerStateEvent.None INSTANCE = null;
        
        private None() {
            super();
        }
    }
}