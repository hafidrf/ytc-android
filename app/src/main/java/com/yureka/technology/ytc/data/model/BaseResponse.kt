package com.yureka.technology.ytc.data.model

data class BaseResponse <T>(
    val data: T?,
    val status: String?,
    val message: String?
)