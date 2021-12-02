package com.example.retrofitpractice

object KmoocService {
    private const val BASE_URL = "http://apis.data.go.kr/B552881/kmooc/"
    val client = BaseService().getClient(BASE_URL)?.create(KmoocApi::class.java)
}