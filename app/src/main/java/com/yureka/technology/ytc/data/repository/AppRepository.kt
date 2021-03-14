package com.yureka.technology.ytc.data.repository

import androidx.lifecycle.liveData
import com.yureka.technology.ytc.data.local.LocalDataSource
import com.yureka.technology.ytc.data.model.RegisterRequest
import com.yureka.technology.ytc.data.model.login.LoginAccess
import com.yureka.technology.ytc.data.remote.RemoteDataSource
import com.yureka.technology.ytc.util.countWeekPassedFromDate
import com.yureka.technology.ytc.util.data.performApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by on 11/25/20.
 */

class AppRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    fun isLoggedIn() = localDataSource.getToken() != null

    fun getWeekPassed() = liveData(Dispatchers.IO) {
        val dateSignUp = localDataSource.getUserDateSigned()
        emit(countWeekPassedFromDate(dateSignUp))
    }

    fun getUserModel() = liveData(Dispatchers.IO) {
        localDataSource.getUserModel()?.let { emit(it) }
    }

    fun login(loginAccess: LoginAccess) = performApiCall(
        networkCall = { remoteDataSource.login(loginAccess) },
        saveCallResult = {
            localDataSource.saveLogin(it)
        }
    )

    fun register(request: RegisterRequest) = performApiCall(
        networkCall = {
            remoteDataSource.register(request)
        },
        saveCallResult = {
            it.data?.let {
                localDataSource.saveRegister(it)
            }
        }
    )

    fun hello(name: String) = performApiCall(
        networkCall = { remoteDataSource.hello(name) },
        saveCallResult = {

        }
    )

    fun logout() = liveData(Dispatchers.IO) {
        localDataSource.logOut()
        emit(true)
    }


}