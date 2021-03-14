package com.yureka.technology.ytc.api

import com.yureka.technology.ytc.data.model.ResponseMessage
import com.yureka.technology.ytc.data.model.login.LoginAccess
import com.yureka.technology.ytc.data.model.login.ResponseLogin
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Web service API.
 * TODO: Replace with your API
 */
interface WebServiceApi {
    @GET("say/hello")
    fun hello(@Query("name") name: String): Single<ResponseMessage>

    @POST("/v1/auth/login")
    fun login(@Body mloginAccess: LoginAccess): Single<ResponseLogin>

}
