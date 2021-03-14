package com.yureka.technology.ytc.data.model.activity


import com.google.gson.annotations.SerializedName

data class ActivityResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("validation")
    val validation: Validation
)