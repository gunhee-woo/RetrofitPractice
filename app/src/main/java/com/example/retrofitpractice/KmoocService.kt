package com.example.retrofitpractice

import com.example.retrofitpractice.network.ApiClient

class KmoocService {
    private val BASE_URL = "http://apis.data.go.kr/B552881/kmooc/"
    val client = ApiClient.getApiClient(BASE_URL)?.create(KmoocApi::class.java)
}