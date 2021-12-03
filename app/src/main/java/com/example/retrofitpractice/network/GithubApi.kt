package com.example.retrofitpractice.network

import com.example.retrofitpractice.models.GithubRepo
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{owner}/repos")
    fun getRepos(@Path("owner") owner: String) : List<GithubRepo>

    @GET("users/{owner}/repos")
    suspend fun getReposSuspend(@Path("owner") owner: String) : List<GithubRepo>

    @GET("users/{owner}/repos")
    suspend fun getReposResponse(@Path("owner") owner: String) : Response<List<GithubRepo>>

    @GET("users/{owner}/repos")
    fun getReposCall(@Path("owner") owner: String) : Call<List<GithubRepo>>

    @GET("users/{owner}/repos")
    fun getReposSingle(@Path("owner") owner: String) : Single<List<GithubRepo>>
}