package com.example.bazandroidcourse.ui.viewmodel

import com.example.bazandroidcourse.domain.GetAllBooksFilteredUseCase
import com.example.bazandroidcourse.domain.GetBookDetailUseCase
import com.example.bazandroidcourse.domain.GetBookOrdersUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class BooksViewModelTest {
    @MockK(relaxed = true)
    lateinit var viewModel: BooksViewModel
    @MockK(relaxed = true)
    lateinit var getAllBooksFilteredUseCaseMockk:  GetAllBooksFilteredUseCase
    @MockK(relaxed = true)
    lateinit var getBookDetailCaseMockK: GetBookDetailUseCase
    @MockK(relaxed = true)
    lateinit var ordersUseCaseMockk: GetBookOrdersUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `fetch all books returns IsNotNull test`() {
        every { viewModel.getAllBooks("btc_mxn") }
        verify(exactly = 1) {viewModel.getAllBooks(any())}
    }

    @Test
    fun `fetch all books returns elements test`() {

    }

    @After
    fun tearDown() {

    }
}