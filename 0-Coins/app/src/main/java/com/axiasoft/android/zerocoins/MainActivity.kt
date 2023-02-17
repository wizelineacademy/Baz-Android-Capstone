package com.axiasoft.android.zerocoins

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.axiasoft.android.zerocoins.common.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = RestfulApi.Builder().build()

        api.getAllDevices().enqueue(
            object: Callback<List<Device>> {
                override fun onResponse(
                    call: Call<List<Device>>,
                    response: Response<List<Device>>
                ) {
                    log("z0", response.body().toString())
                }

                override fun onFailure(call: Call<List<Device>>, t: Throwable) {
                    log("z0", "meh")
                }

            }
        )

       /*runOnUiThread {
           call(api)
        }*/
        //call()
    }

    suspend fun call(api: RestfulApi){

        withContext(Dispatchers.IO){
            val response = api.getAllDevicesOnNaked()

        }

    }
}