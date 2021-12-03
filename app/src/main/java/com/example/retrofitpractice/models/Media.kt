package com.example.retrofitpractice.models

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("image")
    val image: Image,
    @SerializedName("course_image")
    val courseImage: String
)
