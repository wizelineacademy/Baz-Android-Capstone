package com.example.cryptocurrencyapp.utils

import android.content.Context
import androidx.annotation.StringRes
import com.example.cryptocurrencyapp.R

sealed class UiText {
    companion object {
        fun unknownError(): UiText {
            return StringResource(R.string.error_unknown)
        }
    }

    data class StringResource(@StringRes val id: Int) : UiText() {
        override fun getText(context: Context): String {
            return context.getString(id)
        }
    }
    abstract fun getText(context: Context): String
}