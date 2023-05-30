package com.andcourse.domain.utils

import com.andcourse.domain.model.BookModel
import com.andcourse.domain.model.staticdata.API_ICONS
import com.andcourse.domain.model.staticdata.ApplicationCurrency

/***
 * Generates the URL of corresponding crypto currency icon by bookId
 * @param bookId:String is the id of a book of an crypto currency, example: "btc_mxn"
 */
fun createURLImageByBookId(bookId: String): String {
    return createURLImageByTicker(
        getTicker(bookId)
    )
}

/***
 * Gets the ticker part of a bookId, a bookId is formed by ticker_currency
 * @param bookId:String is the id of a book of an crypto currency, example: "btc_mxn"
 */
fun getTicker(bookId: String): String {
    try{
        return bookId.substring(0, bookId.indexOf("_"))
    }catch (e:Exception){
        return ""
    }
}

fun String.getTickerFor():String{
    return getTicker(this)
}

/***
 * Gets the currency part of a bookId, a bookId is formed by ticker_currency
 * @param bookId:String is the id of a book of an crypto currency, example: "btc_mxn"
 */
fun getCurrency(bookId: String): String {
    return bookId.substring(bookId.indexOf("_") + 1)
}



/***
 * Generates the URL of corresponding crypto currency icon by ticker
 * @param ticker:String is the ticker of an crypto currency, example: "btc"
 */
fun createURLImageByTicker(ticker: String) ="${API_ICONS}$ticker"

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
    return ApplicationCurrency.findByTicker(this)?.name ?: this
}
