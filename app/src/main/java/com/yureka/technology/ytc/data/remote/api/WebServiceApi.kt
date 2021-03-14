package com.yureka.technology.ytc.data.remote.api

import com.yureka.technology.ytc.data.model.BaseResponse
import com.yureka.technology.ytc.data.model.RegisterRequest
import com.yureka.technology.ytc.data.model.RegisterResponse
import com.yureka.technology.ytc.data.model.ResponseMessage
import com.yureka.technology.ytc.data.model.login.LoginAccess
import com.yureka.technology.ytc.data.model.login.ResponseLogin
import kotlinx.coroutines.Deferred
import retrofit2.Response
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
    fun hello(@Query("name") name: String): Deferred<Response<ResponseMessage>>

    @POST("auth/login")
    fun loginAsync(@Body mloginAccess: LoginAccess): Deferred<Response<ResponseLogin>>

    @POST("auth/register")
    fun registerAsync(
        @Body request: RegisterRequest
    ): Deferred<Response<BaseResponse<RegisterResponse>>>

}
