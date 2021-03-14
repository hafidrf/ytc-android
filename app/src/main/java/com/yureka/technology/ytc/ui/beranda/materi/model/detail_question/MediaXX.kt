package com.yureka.technology.ytc.ui.beranda.materi.model.detail_question


import com.google.gson.annotations.SerializedName

data class MediaXX(
    @SerializedName("caption")
    val caption: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("public_url")
    val publicUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
)