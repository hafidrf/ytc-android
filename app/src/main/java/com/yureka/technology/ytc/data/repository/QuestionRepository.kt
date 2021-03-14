package com.yureka.technology.ytc.data.repository

import com.yureka.technology.ytc.data.remote.RemoteDataSource
import com.yureka.technology.ytc.util.data.performApiCall
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    fun getQuestionDetails() = performApiCall(
        networkCall = { remoteDataSource.getQuestionDetails()},
        saveCallResult = { }
    )

}