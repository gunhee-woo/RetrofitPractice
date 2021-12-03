package com.example.retrofitpractice.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    val TAG = "" + this@NetworkInterceptor::class.simpleName

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.d(TAG, ", REQUEST method = ${request.method}, url = ${request.url}, connection = ${chain.connection()}")

        val response = chain.proceed(request)
        Log.d(TAG, ", RESPONSE request url = ${response.request.url}, header = ${response.headers}")

        if (response.code == 500) {
            Log.d(TAG, ", intercept : 서버 잘못입니다.")
        } else  {
            Log.d(TAG, ", response code = ${response.code}")
        }

        return response
    }

}

class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request.newBuilder().build())
    }
}