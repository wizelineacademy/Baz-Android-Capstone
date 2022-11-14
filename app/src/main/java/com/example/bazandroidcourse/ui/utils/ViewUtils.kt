package com.example.bazandroidcourse.ui.utils

import com.example.bazandroidcourse.BuildConfig
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.static.ApplicationCurrencies

/***
 * Gets the ticker part of a bookId, a bookId is formed by ticker_currency
 * @param bookId:String is the id of a book of an crypto currency, example: "btc_mxn"
 */
fun getTicker(bookId: String): String {
    return bookId.substring(0, bookId.indexOf("_"))
}

/***
 * Gets the currency part of a bookId, a bookId is formed by ticker_currency
 * @param bookId:String is the id of a book of an crypto currency, example: "btc_mxn"
 */
fun getCurrency(bookId: String): String {
    return bookId.substring(bookId.indexOf("_") + 1)
}

/***
 * Generates the URL of corresponding crypto currency icon by bookId
 * @param bookId:String is the id of a book of an crypto currency, example: "btc_mxn"
 * @param size: int  is the sizer required for the icon, 32 is the default value
 */
fun createURLImageByBookId(bookId: String, size: Int = 32): String {
    return createURLImageByTicker(
        getTicker(bookId),
        size
    )
}

/***
 * Generates the URL of corresponding crypto currency icon by ticker
 * @param ticker:String is the ticker of an crypto currency, example: "btc"
 * @param size: int  is the sizer required for the icon, 32 is the default value
 */
 inline fun createURLImageByTicker(ticker: String, size: Int = 32) = "${BuildConfig.API_ICONS}$size/$ticker"


/***
 * Extension function of a BookModel class,
 * to allows generates your corresponding URL of icon by bookId
 */
 fun BookModel.getIcon(): String {
    return createURLImageByBookId(book)
}

/***
 * Checks if a String matches any of the cryptocurrency names supported by the application, in this case returns the
 * name of the crypto currency otherwise returns the same value
 */
  fun String.cryptoName(): String {
    return ApplicationCurrencies.findByTicker(this)?.name ?: this
}
