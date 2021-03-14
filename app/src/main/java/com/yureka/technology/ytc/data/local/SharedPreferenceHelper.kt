package com.yureka.technology.ytc.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.yureka.technology.ytc.data.model.login.UserModel
import java.util.*
import javax.inject.Inject


class SharedPreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {

    fun saveToken(token: String) = sharedPreferences.edit().putString("token", token).apply()
    fun getToken() = sharedPreferences.getString("token", null)

    fun saveRefreshToken(token: String) = sharedPreferences.edit().putString("refresh_token", token).apply()
    fun getRefreshToken() = sharedPreferences.getString("refresh_token", null)

    fun clear() = sharedPreferences.edit().clear().apply()

    fun saveUser(userModel: UserModel) {
        val json = gson.toJson(userModel)
        sharedPreferences.edit().putString("user", json).apply()
    }

    fun getUser(): UserModel? {
        return try {
            val json = sharedPreferences.getString("user", null)
            gson.fromJson(json, UserModel::class.java)
        } catch (e: JsonSyntaxException) {
            null
        }

    }

    fun saveDateSignedUp(date: Date) = sharedPreferences.edit()
        .putLong("date_signed", date.time).apply()

    fun getDateSigned(): Date = Date(
        sharedPreferences.getLong("date_signed", 0L)
    )


}