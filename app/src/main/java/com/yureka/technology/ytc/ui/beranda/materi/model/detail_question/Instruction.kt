package com.yureka.technology.ytc.ui.beranda.materi.model.detail_question


import com.google.gson.annotations.SerializedName

data class Instruction(
    @SerializedName("media")
    val media: MediaX,
    @SerializedName("text")
    val text: String,
    @SerializedName("with_media")
    val withMedia: Boolean
)