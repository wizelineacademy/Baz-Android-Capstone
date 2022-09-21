package com.example.cryptocurrency_challenge
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrency_challenge.adapter.CurrencyAdapter
import com.example.cryptocurrency_challenge.network.api.config.StartApplication.Companion.webServiceGlobal
import com.example.cryptocurrency_challenge.databinding.ActivityMainBinding
import com.example.cryptocurrency_challenge.model.Available_books_response
import com.example.cryptocurrency_challenge.viewmodel.CryptocurrencyViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
* Proyecto desarrollado durante entrenamiento en Wizeland
* https://github.com/ccanon28/baz-android-course/tree/main
* Autor:   Carlos Roberto Cano Noguera
* Mentor:  Fabian Jiménez Villegas
* */

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CryptocurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get()

        call_available_books()
    }

    private fun call_available_books(){

        val callRespuesta= webServiceGlobal.getAllCurrencies()
        callRespuesta.enqueue(object : Callback<Available_books_response> {
            override fun onResponse(
                call: Call<Available_books_response>,
                response: Response<Available_books_response>
            ) {
                if(response.isSuccessful){
                    response.let {
                        val responseList = response.body()?.payload
                        val list_mxn_currencys = responseList?.filter { it.book.contains("mxn") }
                        //Log.i("Response", "list_mxn_currencys: ${list_mxn_currencys}")
                        if(list_mxn_currencys!=null && list_mxn_currencys.size>0){
                            //Log.i("Response", "Available_books_response: ${list_mxn_currencys}")
                            val adaptador = CurrencyAdapter(list_mxn_currencys)
                            binding.recyclerview.adapter= adaptador
                            binding.recyclerview.layoutManager= LinearLayoutManager(applicationContext)
                        }
                        else{
                            Toast.makeText(applicationContext, "Lista vacia", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<Available_books_response>, t: Throwable) {
                Log.i("ERROR", "ERROR")
            }
        })
    }

}