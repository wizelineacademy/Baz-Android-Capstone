
package com.androidcourse.data.remote

import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CryptoRemoteDataSourceImplTest {

    // Rule that wanna be used to dispatcher
    //@get:Rule
    //var rule: TestRule = InstantTaskExecutorRule()

    // Data mock
    private val mockAPIInterface = mockk<com.androidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface>(relaxed = true)
    private lateinit var targetTest: com.androidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface

    private lateinit var currentBookId: String
    private val dispatcher = TestCoroutineDispatcher()
    private val scope = CoroutineScope(
        SupervisorJob() + dispatcher
    )

    @Before
    fun setup() {
        currentBookId = "xrp_mxn"
        Dispatchers.setMain(dispatcher)
        targetTest = com.androidcourse.data.datasource.remote.CryptoRemoteDataSourceImpl(
            mockAPIInterface,
            scope
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `fetchBookDetail invokes repository method once test`() = runBlocking {
        // Given
        val fakeDetailResponse =
            com.androidcourse.data.datasource.remote.api.response.DetailResponse(
                book = currentBookId,
                low = "100",
                high = "1009",
                volume = "73293893",
                last = "838"
            )
        coEvery {
            mockAPIInterface.fetchBookDetail(currentBookId)
        } returns mockk(relaxed = true) { fakeDetailResponse }
        // When
        val result = targetTest.fetchBookDetail(currentBookId)
        // Then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(com.androidcourse.data.datasource.remote.api.response.DetailResponse::class.java)
        coVerify(exactly = 1) { mockAPIInterface.fetchBookDetail(currentBookId) }
    }

    @Test
    fun `fetchBookDetail returns correct data test`() = runBlocking {
        // Given
        val fakeDetailResponse =
            com.androidcourse.data.datasource.remote.api.response.DetailResponse(
                book = "xrp_mxn",
                low = "100",
                high = "1009",
                volume = "73293893",
                last = "838"
            )
        coEvery {
            mockAPIInterface.fetchBookDetail("xrp_mxn")
        } returns mockk(relaxed = true) { fakeDetailResponse }
        // When
        val result = targetTest.fetchBookDetail("xrp_mxn")
        // Then
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(com.androidcourse.data.datasource.remote.api.response.DetailResponse::class.java)
    }
}
