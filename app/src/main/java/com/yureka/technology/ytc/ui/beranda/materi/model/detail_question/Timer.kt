package com.yureka.technology.ytc.ui.beranda.materi.model.detail_question


import com.google.gson.annotations.SerializedName

data class Timer(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("start_trigger")
    val startTrigger: String
)