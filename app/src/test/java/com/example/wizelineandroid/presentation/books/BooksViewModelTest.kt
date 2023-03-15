package com.example.wizelineandroid.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.wizelineandroid.data.remote.model.Books
import com.example.wizelineandroid.data.remote.model.GetTicker
import com.example.wizelineandroid.data.remote.model.GetTickers
import com.example.wizelineandroid.data.remote.model.ModelBook
import com.example.wizelineandroid.repository.available.BooksRepo
import com.example.wizelineandroid.repository.ticker.TickerRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BooksViewModelTest{
    @RelaxedMockK
    private lateinit var repo: BooksRepo

    @get:Rule
    var instantExcecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `when get coins from book`() = runBlocking {
        val list =  listOf(ModelBook("btc","646","7575"))
        val infoBook = Books(list,true)
        //given
        coEvery { repo.getAvailableBooks() } returns infoBook

        //when
        val response = repo.getAvailableBooks()

        //then
        coVerify(exactly = 1) { repo.getAvailableBooks() }
        assert(infoBook == response)
    }
}