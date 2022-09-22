package com.example.criptobitsoproyectwz.data.network

import android.database.Observable
import com.example.criptobitsoproyectwz.data.model.Criptos.BaseResult
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class CriptosClientRx(private val criptoServiceRx: BitsoService) {
    fun getCriptosRx(): Single<BaseResult> = criptoServiceRx.getCriptosServiceRX()
    fun getCriptosRx1(): Single<BaseResult> = criptoServiceRx.getCriptosServiceRX()
    fun getCriptosRx2(): Single<BaseResult> = criptoServiceRx.getCriptosServiceRX()
}