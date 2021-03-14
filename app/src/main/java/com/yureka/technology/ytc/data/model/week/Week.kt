package com.yureka.technology.ytc.data.model.week


import com.google.gson.annotations.SerializedName

data class Week(
    @SerializedName("caption")
    val caption: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("topic")
    val topic: String,
    @SerializedName("video")
    val video: String
)