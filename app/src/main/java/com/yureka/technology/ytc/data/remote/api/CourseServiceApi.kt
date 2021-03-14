package com.yureka.technology.ytc.data.remote.api

import com.yureka.technology.ytc.data.model.week.WeeksResponse
import com.yureka.technology.ytc.data.model.activity.ActivityResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CourseServiceApi {

    @Headers("X-Company-ID: 5fad2ff6d9c15a03ece5272a", "mock:true")
    @GET("/course/v1/weeks")
    fun getWeeks(): Deferred<Response<WeeksResponse>>

    @Headers("X-Company-ID: 5fad2ff6d9c15a03ece5272a", "mock:true")
    @GET("/course/v1/activities")
    fun getActivitiesByWeek(@Query("week_id") week_id: String): Deferred<Response<ActivityResponse>>
}