package com.example.bazandroidcourse.data.datasource.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bazandroidcourse.data.datasource.remote.api.response.BookResponse
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CryptoRemoteDataSourceImplTest {

    // Rule that wanna be used to dispatcher
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    // Data mock
    private val mockAPIInterface = mockk<ApplicationAPIInterface>(relaxed = true)

    private lateinit var targetTest: CryptoRemoteDataSourceInterface

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        targetTest = CryptoRemoteDataSourceImpl(mockAPIInterface)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `test fetchAllBooks success response`() = runBlocking {
        coEvery { mockAPIInterface.fetchAvailableBooks() } returns mockk(relaxed = true) {
            coEvery { payload } returns listOf(BookResponse(book = "btc_mx"))
        }
        val result = targetTest.fetchAllBooks()
        assertThat(result).isNotEmpty()
        assertThat(result).isInstanceOf(List::class.java)
        assertThat(result[0].book).isEqualTo("btc_mx")
    }

    @Test
    fun `test fetchAllBooks failure response`() = runBlocking {
        coEvery { mockAPIInterface.fetchAvailableBooks() } returns mockk(relaxed = true) {
            coEvery { payload } returns listOf()
        }
        val result = targetTest.fetchAllBooks()
        assertThat(result).isEmpty()
        assertThat(result).isInstanceOf(List::class.java)
    }
}