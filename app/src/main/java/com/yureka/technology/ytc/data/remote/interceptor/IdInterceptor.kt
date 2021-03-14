package com.yureka.technology.ytc.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created on 12/9/20.

 */

class IdInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val req = chain.request().newBuilder()

        req.header("X-Company-ID", "5fbb7ef15c3c940dd916f324")
        req.header("X-Guest-ID", "abcd1234")

        return chain.proceed(req.build())

    }

}