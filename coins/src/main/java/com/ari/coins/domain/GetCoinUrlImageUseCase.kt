package com.ari.coins.domain

import com.ari.coins.domain.contracts.UseCase
import javax.inject.Inject

class GetCoinUrlImageUseCase @Inject constructor(): UseCase<String, String> {
    override fun invoke(book: String): String =
        "https://firebasestorage.googleapis.com/v0/b/crypto-d6420.appspot.com/o/cryptocurrency_icon%2Fic_crypto_${book.split("_")[0]}.png?alt=media"
}