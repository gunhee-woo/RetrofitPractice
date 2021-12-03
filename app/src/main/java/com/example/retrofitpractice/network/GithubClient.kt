package com.example.retrofitpractice.network

class GithubClient {
    private val BASE_URL = "https://api.github.com"
    val client: GithubApi? = ApiClient.getApiClient(BASE_URL)?.create(GithubApi::class.java)
}