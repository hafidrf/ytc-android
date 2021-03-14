package com.yureka.technology.ytc.data.model.week


import com.yureka.technology.ytc.data.model.week.Data
import com.yureka.technology.ytc.data.model.week.Validation
import com.google.gson.annotations.SerializedName

data class WeeksResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("validation")
    val validation: Validation
)