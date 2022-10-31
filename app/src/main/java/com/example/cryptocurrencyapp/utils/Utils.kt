package com.example.cryptocurrencyapp.utils

import java.text.Normalizer

object Utils {
    fun cleanString(texto: String): String? {
        var texto = texto
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD)
        texto = texto.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
        return texto
    }
}