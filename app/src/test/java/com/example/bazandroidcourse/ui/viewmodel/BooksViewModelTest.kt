package com.example.bazandroidcourse.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.repository.BooksRepositoryImpl
import com.example.bazandroidcourse.domain.GetAllBooksFilteredUseCase
import com.example.bazandroidcourse.domain.GetBookDetailUseCase
import com.example.bazandroidcourse.domain.GetBookOrdersUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BooksViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var repositoryMockK: BooksRepositoryImpl

    @MockK(relaxed = true)
    lateinit var viewModel: BooksViewModel

    @MockK(relaxed = true)
    lateinit var getAllBooksFilteredUseCaseMockk: GetAllBooksFilteredUseCase

    @MockK(relaxed = true)
    lateinit var getBookDetailCaseMockK: GetBookDetailUseCase

    @MockK(relaxed = true)
    lateinit var ordersUseCaseMockk: GetBookOrdersUseCase

    private  val dispatcher = TestCoroutineDispatcher()//StandardTestDispatcher()


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(
            dispatcher
        )
        viewModel = BooksViewModel(
            getAllBooksFilteredUseCaseMockk,
            getBookDetailCaseMockK,
            ordersUseCaseMockk,
            dispatcher
        )
    }

    @Test
    fun `verify all books is invoked once`() = runBlocking {
        //Given
        coEvery { getAllBooksFilteredUseCaseMockk.invoke(any()) } returns emptyList()
        coEvery { repositoryMockK.getAllBooks() } returns emptyList()

        //When
        viewModel.getAllBooks("btc")

        //Then
        coVerify(exactly = 1) { getAllBooksFilteredUseCaseMockk("btc") }
    }

    @Test
    fun `fetch all books returns IsNotNull test`() {
        viewModel.getAllBooks("btc")
        assertThat(viewModel.allBooks).isNotNull()
    }

    @Test
    fun `fetch all books returns correct data `() = runBlocking {
        //given
        val books = listOf(
            BookModel("btc", "Bitcoin"),
            BookModel("eth", "Etherium"),
            BookModel("xrp", "Ripple"),
        )
        coEvery { getAllBooksFilteredUseCaseMockk("btc") } returns books

        //when
        viewModel.getAllBooks("btc")
        val allBooksValue = viewModel.allBooks.value

        //then
        assertThat(allBooksValue).isEqualTo(books)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}