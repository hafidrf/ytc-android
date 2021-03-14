package com.yureka.technology.ytc.data.model.activity


import com.google.gson.annotations.SerializedName

data class Activity(
    @SerializedName("desc")
    val desc: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("questions")
    val questions: List<String>,
    @SerializedName("title")
    val title: String
)