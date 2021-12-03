package com.example.retrofitpractice.models

import com.google.gson.annotations.SerializedName

data class LectureResponse(
    @SerializedName("pagination")
    val page: Page,
    @SerializedName("results")
    val results: List<Lecture>
)
