package com.example.wizelineproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wizelineproject.domain.model.Book
import com.example.wizelineproject.domain.model.Ticker
import com.example.wizelineproject.domain.repository.CriptosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = CriptosRepository()
        CoroutineScope(Dispatchers.IO).launch {
            repo.getOrderBooks{ success, data ->
                if(success) {
                    Log.e("log", "EXITO desde main" + success + " ")
                    Log.e("log", data.toString())
                }
                else
                    Log.e("log", "FALLO desde main ")
            }
            /*repo.getTickers { success, data ->
                if(success) {
                    Log.e("log", "desde el main te digo que hubo EXITO2" + success + " ")
                    data.forEach {
                        Log.e("log", it.toString())
                    }
                }
                else
                    Log.e("log", "FALLO")
            }*/
            /*repo.getBooks { success:Boolean, data: List<Book> -> Double
                if(success) {
                    Log.e("log", "desde el main te digo que hubo EXITO2" + success+" ")
                    data.forEach {
                        println(it.book)
                    }
                }
                else
                    Log.e("log", "desde el main te digo que hubo 4FRACASO"+success)

                0.0
            }
        }*/

        }
    }
}