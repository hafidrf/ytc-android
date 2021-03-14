package com.yureka.technology.ytc.data.remote.interceptor

import com.yureka.technology.ytc.data.local.SharedPreferenceHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by on 11/25/20.
 */
class AuthInterceptor @Inject constructor(private val sessionHelper: SharedPreferenceHelper) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val req = chain.request()
        val builder = req.newBuilder()

        val token = sessionHelper.getToken()
        if (req.header("Authorization") == null) {
            if (token != null) builder.header("Authorization", "Bearer $token")
        }

        return chain.proceed(builder.build())
    }
}