package com.example.cryptocurrency_challenge.domain

import com.example.cryptocurrency_challenge.data.model.AvailableBooksResponse
import com.example.cryptocurrency_challenge.data.model.Payload
import com.example.cryptocurrency_challenge.repository.AvailableBooksRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class AvailableBooksUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: AvailableBooksRepository

    lateinit var availableBooksUseCase : AvailableBooksUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        availableBooksUseCase = AvailableBooksUseCase(repository)
    }

    @Test
    fun `Cuando La Api AvailableBooks Responde Exitosamente`() = runBlocking {

        //Given
        val myListTest = AvailableBooksResponse(
            payload = listOf(
                Payload(""),
                Payload("")
            ),
            success = true
        )

        coEvery { repository.getAvailableBooks() } returns myListTest.payload

        //When
        val response = availableBooksUseCase.invoke().isEmpty()

        //Then
        assert( response == myListTest.payload.isNotEmpty())

    }
}