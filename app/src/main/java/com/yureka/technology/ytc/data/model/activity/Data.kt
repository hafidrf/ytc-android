package com.yureka.technology.ytc.data.model.activity


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("activities")
    val activities: List<Activity>
)