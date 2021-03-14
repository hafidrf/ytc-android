package com.yureka.technology.ytc.data.repository

import com.yureka.technology.ytc.data.remote.RemoteDataSource
import com.yureka.technology.ytc.util.data.performApiCall
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    fun getWeeks() = performApiCall(
        networkCall = { remoteDataSource.getWeeks() },
        saveCallResult = {

        }
    )

    fun getActivitiesByWeek(weekId: String) = performApiCall(
        networkCall = { remoteDataSource.getActivitiesByWeek(weekId) },
        saveCallResult = {

        }
    )

}