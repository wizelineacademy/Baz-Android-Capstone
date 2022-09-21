package com.example.cryptocurrency_challenge.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.cryptocurrency_challenge.databinding.FragmentDetailCurrencysBinding
import com.example.cryptocurrency_challenge.model.InfoTickerResponse
import com.example.cryptocurrency_challenge.model.Payload_Ticker
import com.example.cryptocurrency_challenge.network.api.config.StartApplication
import com.example.cryptocurrency_challenge.viewmodel.CryptocurrencyViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("SetTextI18n")
class DetailCurrencysFragment : Fragment() {

    private var _binding: FragmentDetailCurrencysBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CryptocurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCurrencysBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get()


        call_info_ticker()



    }


    private fun call_info_ticker(){

        val callRespuesta= StartApplication.webServiceGlobal.getInfoTicker()
        callRespuesta.enqueue(object : Callback<InfoTickerResponse> {
            override fun onResponse(
                call: Call<InfoTickerResponse>,
                response: Response<InfoTickerResponse>
            ) {
                if(response.isSuccessful){
                    val responseTickerAux = response.body()?.payload
                    initView(responseTickerAux)

                }
            }

            override fun onFailure(call: Call<InfoTickerResponse>, t: Throwable) {
                binding.txtLastPrice.text = "Fallo consumo de servicio"
                Log.i("ERROR", "ERROR")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun initView(responseTickerAux: Payload_Ticker?) {
        binding.txtLastPrice.text= ""
    }
}
