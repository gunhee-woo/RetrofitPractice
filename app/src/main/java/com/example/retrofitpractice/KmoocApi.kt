package com.example.retrofitpractice

import com.example.retrofitpractice.models.LectureList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KmoocApi {
    @GET("courseList")
    suspend fun getCourseList(
        @Query("serviceKey") serviceKey: String,
        @Query("Mobile") mobile: Int
    ): Response<LectureList>
}