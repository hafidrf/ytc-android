package com.yureka.technology.ytc.data.model

import com.google.gson.annotations.SerializedName



data class RegisterRequest(
    val name: String, val email: String, val password: String, val phone: String, val age: String
)

data class RegisterResponse(
    val name: String,
    val email: String,
    val token: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expired_at") val expiredAt: Long
)