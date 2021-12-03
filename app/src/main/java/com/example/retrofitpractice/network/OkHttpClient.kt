package com.example.retrofitpractice.network

import com.example.retrofitpractice.network.AppInterceptor
import com.example.retrofitpractice.network.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

fun createOkHttpClient() = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addNetworkInterceptor(NetworkInterceptor())
        .addInterceptor(AppInterceptor())
        .build()

fun createOkHttpLogClient()= OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor())
        .build()