package com.yureka.technology.ytc.ui.beranda.materi.model.detail_question


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("company")
    val company: Company,
    @SerializedName("correct_answer")
    val correctAnswer: List<String>,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("explaination")
    val explaination: Explaination,
    @SerializedName("id")
    val id: String,
    @SerializedName("instruction")
    val instruction: Instruction,
    @SerializedName("options")
    val options: List<String>,
    @SerializedName("point")
    val point: Int,
    @SerializedName("question")
    val question: Question,
    @SerializedName("timer")
    val timer: Timer,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("upload_settings")
    val uploadSettings: UploadSettings
)