package com.axiasoft.android.zerocoins.network.bitso.security

import com.axiasoft.android.zerocoins.BuildConfig
import com.axiasoft.android.zerocoins.network.bitso.security.BitsoKeys.API_KEY
import com.axiasoft.android.zerocoins.network.bitso.security.BitsoKeys.API_SECRET
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object BitsoKeys {
    const val API_KEY = BuildConfig.BITSO_KEY
    const val API_SECRET = BuildConfig.BITSO_SECRET
}

object BitsoAuthentication{
    var nonce: Long = Long.MIN_VALUE
        set(value) { field = System.currentTimeMillis() / 1000 }

    private fun genSignature(method: String, path: String, json: String): String{
        nonce = 0
        val buffer = StringBuffer().apply {
            append(nonce)
            append(method)
            append(path)
            append(json)
        }
        return hMacCypher(msg = buffer.toString(), key = API_SECRET)
    }
    fun genAuthHeader(method: String, path: String, json: String = String()): String{
        val signature = genSignature(method, path, json)
        return "Bitso $API_KEY:$nonce:$signature"
    }

    private fun hMacCypher(msg: String, key: String, alg: String = "HmacSHA256"): String {
        val signingKey = SecretKeySpec(key.toByteArray(), alg)
        val mac = Mac.getInstance(alg)
        mac.init(signingKey)
        val bytes = mac.doFinal(msg.toByteArray())
        return format(bytes)
    }

    //TODO verify format required
    private fun format(bytes: ByteArray): String {
        val formatter = Formatter()
        bytes.forEach { formatter.format("%02x", it) }
        return formatter.toString()
    }
}