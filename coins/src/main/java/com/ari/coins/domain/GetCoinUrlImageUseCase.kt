package com.ari.coins.domain

import com.ari.coins.domain.contracts.UseCase
import javax.inject.Inject

/**
 * @author Ari Valencia
 * @file GetCoinUrlImageUseCase
 * @description This UseCase returns an url image of book param
 */

class GetCoinUrlImageUseCase @Inject constructor() : UseCase<String, String> {
    override fun invoke(book: String): String =
        Constants.COIN_IMAGE_BASE_URL + book.split("_")[0] + Constants.COIN_IMAGE_END_URL
}
