package com.yureka.technology.ytc.data.remote

import com.yureka.technology.ytc.data.model.RegisterRequest
import com.yureka.technology.ytc.data.model.login.LoginAccess
import com.yureka.technology.ytc.data.remote.api.CourseServiceApi
import com.yureka.technology.ytc.data.remote.api.QuestionServiceApi
import com.yureka.technology.ytc.data.remote.api.WebServiceApi
import com.yureka.technology.ytc.util.base.BaseRemoteDataSource
import javax.inject.Inject

/**
 * Created on 11/25/20.
 */

class RemoteDataSource @Inject constructor(
    private val webServiceApi: WebServiceApi,
    private val courseServiceApi: CourseServiceApi,
    private val questionServiceApi: QuestionServiceApi
) : BaseRemoteDataSource(){

    suspend fun login(loginAccess: LoginAccess) = getResult { webServiceApi.loginAsync(loginAccess) }

    suspend fun hello(name: String) = getResult { webServiceApi.hello(name) }

    suspend fun getWeeks() = getResult { courseServiceApi.getWeeks() }

    suspend fun getActivitiesByWeek(weekId: String) = getResult { courseServiceApi.getActivitiesByWeek(weekId) }

    suspend fun getQuestionDetails() = getResult { questionServiceApi.getDetailsQuestion() }

    suspend fun register(request: RegisterRequest) = getResult { webServiceApi.registerAsync(request) }

}