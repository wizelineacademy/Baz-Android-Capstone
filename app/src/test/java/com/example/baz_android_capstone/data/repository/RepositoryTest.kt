package com.example.baz_android_capstone.data.repository

import com.example.baz_android_capstone.data.db.BookDatabase
import com.example.baz_android_capstone.data.models.availableBook.Book
import com.example.baz_android_capstone.data.models.availableBook.Payload
import com.example.baz_android_capstone.data.network.BookAPI
import com.example.baz_android_capstone.util.Resource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RepositoryTest {
    @Mock
    private lateinit var api: BookAPI

    @Mock
    private lateinit var db: BookDatabase

    private lateinit var repository: Repository

    private val bookResponse = Resource.Success<Book>(
        data = Book(
            success = true,
            payload = listOf(
                Payload("btc_mxn"),
                Payload("eth_mxn"),
                Payload("xrp_mxn"),
                Payload("ltc_mxn"),
                Payload("bch_btc"),
                Payload("bch_mxn"),
                Payload("tusd_btc"),
                Payload("tusd_mxn"),
                Payload("mana_btc"),
                Payload("mana_mxn"),
                Payload("bat_btc")
            )
        )
    )

    @Before
    fun setup() {
        api = mock()
        db = mock()
        repository = Repository(api, db)
    }

    @Test
    fun `test getBooks is not null`() {
        runBlocking {
            // given
            // whenever(api.getAllAvailableBooks()).thenReturn(bookResponse)
            // whenever(db.bookDao().getBooks())
            whenever(repository.getBooks().first()).thenReturn(bookResponse)

            // when
            val response = repository.getBooks()

            // then
            Assert.assertNotNull(response)
        }
    }
}
