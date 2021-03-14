package com.yureka.technology.ytc.ui.beranda.materi.model.detail_question


import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("media")
    val media: MediaXX,
    @SerializedName("text")
    val text: String,
    @SerializedName("with_media")
    val withMedia: Boolean
)