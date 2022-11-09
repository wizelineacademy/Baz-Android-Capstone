package com.example.cryptocurrencyapp.domain.use_case


import com.example.cryptocurrencyapp.data.remote.entity.WCCryptoOrderBook
import com.example.cryptocurrencyapp.data.remote.entity.response.WCCOrder
import com.example.cryptocurrencyapp.data.remote.entity.response.WCCryptoOrderResponse
import com.example.cryptocurrencyapp.data.repository.CryptoRespository
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.entity.WCCOrderBookDTO
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.example.cryptocurrencyapp.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations


internal class DetailUseCaseTest {
    @RelaxedMockK
    private var  coinRepository: CryptoRespository = mockk()
    lateinit var getCoinUseCase: DetailUseCase

   @Before
    fun onBefore(){
        MockitoAnnotations.initMocks(this)
       getCoinUseCase = DetailUseCase(coinRepository)
    }

   /* @Test
    fun `when submit data with a incorrect request and success status is expected`() = runBlocking{
        //GIVEN
        val mockListAsk: List<WCCOrderBookDTO> = listOf(
            WCCOrderBookDTO(price = "12", amount = "15"),
            WCCOrderBookDTO(price = "12", amount = "33")
        )
        val mockListBid: List<WCCOrderBookDTO> = listOf(
            WCCOrderBookDTO(price = "12", amount = "15"),
            WCCOrderBookDTO(price = "12", amount = "33")
        )
        val mockData = WCCOrdeRDTO(mockListAsk,mockListBid)
       // coEvery { coinRepository.getOrderBook("btc_mxn") } returns Resource.Success(mockData)
        //WHEN
        val result = getCoinUseCase.order("etc_mxn").last()

        //THEN
        Assert.assertTrue(result is Resource.Success)
    }



    @Test
    fun `when the api does not return then get values from data base`()= runBlocking {
        //GIVEN
        coEvery { coinRepository.getTickerBook("btc_mxn")} returns WCCTickerDTO()

        //WHEN
        getCoinUseCase.ticker("btc_mxn")

        //THEN
        coVerify (exactly = 1){ getCoinUseCase.ticker("btc_mxn")}
    }*/
}