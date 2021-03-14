package com.yureka.technology.ytc.data.local

import com.yureka.technology.ytc.data.model.RegisterResponse
import com.yureka.technology.ytc.data.model.login.ResponseLogin
import com.yureka.technology.ytc.data.model.login.UserModel
import com.yureka.technology.ytc.util.base.BaseLocalDataSource
import com.yureka.technology.ytc.util.toDateOrNull
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val pref: SharedPreferenceHelper
) : BaseLocalDataSource() {

    fun getToken() = pref.getToken()

    fun getUserModel() = pref.getUser()


    fun logOut() = pref.clear()

    fun saveLogin(responseLogin: ResponseLogin) {

        responseLogin.data.let {

            val user = UserModel(
                name = "${it.firstname} ${it.lastname}",
                email = it.email
            )

            pref.saveUser(user)

            pref.saveToken(it.token)

            it.created_at.toDateOrNull()?.let {
                pref.saveDateSignedUp(it)
            }

        }

    }

    fun saveRegister(data: RegisterResponse){

        val user = UserModel(name = data.name, email = data.email)

        pref.saveUser(user)
        pref.saveToken(data.token)
        pref.saveRefreshToken(data.refreshToken)

    }

    fun getUserDateSigned() = pref.getDateSigned()


}