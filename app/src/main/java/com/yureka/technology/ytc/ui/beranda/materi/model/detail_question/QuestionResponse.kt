package com.yureka.technology.ytc.ui.beranda.materi.model.detail_question


import com.google.gson.annotations.SerializedName

data class QuestionResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("validation")
    val validation: Validation
)