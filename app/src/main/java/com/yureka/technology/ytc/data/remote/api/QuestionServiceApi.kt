package com.yureka.technology.ytc.data.remote.api

import com.yureka.technology.ytc.ui.beranda.materi.model.detail_question.QuestionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface QuestionServiceApi {

    @Headers("mock:true")
    @GET("/question/detail")
    fun getDetailsQuestion(): Deferred<Response<QuestionResponse>>
}