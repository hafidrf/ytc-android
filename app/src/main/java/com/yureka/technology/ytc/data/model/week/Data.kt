package com.yureka.technology.ytc.data.model.week


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("weeks")
    val weeks: List<Week>
)