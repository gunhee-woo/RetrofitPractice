package com.example.retrofitpractice.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("raw")
    val raw: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("large")
    val large: String
)
