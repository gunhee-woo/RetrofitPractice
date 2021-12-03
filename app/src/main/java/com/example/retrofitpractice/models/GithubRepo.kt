package com.example.retrofitpractice.models

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("private")
    val private: Boolean,
    @SerializedName("owner")
    val owner: Owner
)
