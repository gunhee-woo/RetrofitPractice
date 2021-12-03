package com.example.retrofitpractice.models

import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("count")
    val count: Int,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("num_pages")
    val numPages: Int,
    @SerializedName("next")
    val next: String
)
