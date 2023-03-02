package com.javg.cryptocurrencies.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CRYBookTest{
    private val target = CRYBook(
        book = "btc_mxn",
        imageUrl = "https://cryptoicons.org/api/icon/dai/200",
        bookDestination = "https://cryptoicons.org/api/icon/dai/200")
    private val target2 = CRYDetailBook()
    private val target3 = CRYAskOrBids(
        book = "btc_mxn",
        price = "123",
        amount = "123456")

    @Test
    fun `test cryBook with custom properties`(){
        assertThat(target.book).isNotEmpty()
        assertThat(target.imageUrl).isNotEmpty()
        assertThat(target.bookDestination).isNotEmpty()
    }

    @Test
    fun `test cryBook with empty properties`(){
        target.apply {
            book     = ""
            imageUrl = ""
            bookDestination = ""
        }
        assertThat(target.book).isEmpty()
        assertThat(target.imageUrl).isEmpty()
        assertThat(target.bookDestination).isEmpty()
    }

    @Test
    fun `test cryDetailBook default properties`(){
        assertThat(target2.high).isEmpty()
        assertThat(target2.low).isEmpty()
        assertThat(target2.last).isEmpty()
        assertThat(target2.askList).isNull()
        assertThat(target2.bidsList).isNull()
    }

    @Test
    fun `test cryDetailBook with a custom property`(){
        target2.apply {
            high = "1234"
        }
        assertThat(target2.high).isNotEmpty()
        assertThat(target2.low).isEmpty()
        assertThat(target2.last).isEmpty()
        assertThat(target2.askList).isNull()
        assertThat(target2.bidsList).isNull()
    }

    @Test
    fun `test cryDetailBook with two custom property`(){
        target2.apply {
            high = "1234"
            low = "123455667"
        }
        assertThat(target2.high).isNotEmpty()
        assertThat(target2.low).isNotEmpty()
        assertThat(target2.last).isEmpty()
        assertThat(target2.askList).isNull()
        assertThat(target2.bidsList).isNull()
    }

    @Test
    fun `test cryDetailBook with three custom property`(){
        target2.apply {
            high = "1234"
            low  = "123455667"
            last = "123456789"
        }
        assertThat(target2.high).isNotEmpty()
        assertThat(target2.low).isNotEmpty()
        assertThat(target2.last).isNotEmpty()
        assertThat(target2.askList).isNull()
        assertThat(target2.bidsList).isNull()
    }

    @Test
    fun `test cryDetailBook with empty list ask`(){
        target2.apply {
            high = "1234"
            low  = "123455667"
            last = "123456789"
            askList = mutableListOf()
        }
        assertThat(target2.high).isNotEmpty()
        assertThat(target2.low).isNotEmpty()
        assertThat(target2.last).isNotEmpty()
        assertThat(target2.askList).isEmpty()
        assertThat(target2.bidsList).isNull()
    }

    @Test
    fun `test cryDetailBook with empty list bids`(){
        target2.apply {
            high = "1234"
            low  = "123455667"
            last = "123456789"
            bidsList = mutableListOf()
        }
        assertThat(target2.high).isNotEmpty()
        assertThat(target2.low).isNotEmpty()
        assertThat(target2.last).isNotEmpty()
        assertThat(target2.askList).isNull()
        assertThat(target2.bidsList).isEmpty()
    }

    @Test
    fun `test cryDetailBook with list ask`(){
        target2.apply {
            high = "1234"
            low  = "123455667"
            last = "123456789"
            askList = mutableListOf<CRYAskOrBids>().apply {
                add(CRYAskOrBids(
                    book = "12345",
                    price = "123",
                    amount = "1234567"))
            }
        }
        assertThat(target2.high).isNotEmpty()
        assertThat(target2.low).isNotEmpty()
        assertThat(target2.last).isNotEmpty()
        assertThat(target2.askList).isNotEmpty()
        assertThat(target2.bidsList).isNull()
    }

    @Test
    fun `test cryDetailBook with list bids`(){
        target2.apply {
            high = "1234"
            low  = "123455667"
            last = "123456789"
            bidsList = mutableListOf<CRYAskOrBids>().apply {
                add(CRYAskOrBids(
                    book = "12345",
                    price = "123",
                    amount = "1234567"))
            }
        }
        assertThat(target2.high).isNotEmpty()
        assertThat(target2.low).isNotEmpty()
        assertThat(target2.last).isNotEmpty()
        assertThat(target2.askList).isNull()
        assertThat(target2.bidsList).isNotEmpty()
    }

    @Test
    fun `test cryDetailBook custom all properties`(){
        target2.apply {
            high = "1234"
            low  = "123455667"
            last = "123456789"
            askList  = mutableListOf<CRYAskOrBids>().apply {
                add(CRYAskOrBids(
                    book = "12345",
                    price = "123",
                    amount = "1234567"))
            }
            bidsList = mutableListOf<CRYAskOrBids>().apply {
                add(CRYAskOrBids(
                    book = "12345",
                    price = "123",
                    amount = "1234567"))
            }
        }
        assertThat(target2.high).isNotEmpty()
        assertThat(target2.low).isNotEmpty()
        assertThat(target2.last).isNotEmpty()
        assertThat(target2.askList).isNotEmpty()
        assertThat(target2.bidsList).isNotEmpty()
    }

    @Test
    fun `test cryAskOrBids with custom properties`(){
        assertThat(target3.book).isNotEmpty()
        assertThat(target3.price).isNotEmpty()
        assertThat(target3.amount).isNotEmpty()
    }

    @Test
    fun `test cryAskOrBids with empty properties`(){
        target3.apply {
            book   = ""
            price  = ""
            amount = ""
        }
        assertThat(target3.book).isEmpty()
        assertThat(target3.price).isEmpty()
        assertThat(target3.amount).isEmpty()
    }

    @Test
    fun `test cryAskOrBids with empty book property`(){
        target3.apply {
            book   = ""
            price  = "123"
            amount = "123456789"
        }
        assertThat(target3.book).isEmpty()
        assertThat(target3.price).isNotEmpty()
        assertThat(target3.amount).isNotEmpty()
    }

    @Test
    fun `test cryAskOrBids with empty price property`(){
        target3.apply {
            book   = "btc_mxn"
            price  = ""
            amount = "123456789"
        }
        assertThat(target3.book).isNotEmpty()
        assertThat(target3.price).isEmpty()
        assertThat(target3.amount).isNotEmpty()
    }

    @Test
    fun `test cryAskOrBids with empty amount property`(){
        target3.apply {
            book   = "btc_mxn"
            price  = "1234"
            amount = ""
        }
        assertThat(target3.book).isNotEmpty()
        assertThat(target3.price).isNotEmpty()
        assertThat(target3.amount).isEmpty()
    }
}