package com.yureka.technology.ytc.util.livesharedpreference

import android.content.SharedPreferences

/**
 * Created on 2/7/20.
 */

class StringLiveSharedPreference(prefs: SharedPreferences?, key: String?, defValue: String) :
    SharedPreferenceLiveData<String>(prefs, key, defValue) {

    override fun getValueFromPreferences(key: String?, defValue: String?): String =
        sharedPrefs.getString(key, defValue).toString()

}


class BooleanLiveSharedPreference(prefs: SharedPreferences?, key: String?, defValue: Boolean) :
    SharedPreferenceLiveData<Boolean>(prefs, key, defValue) {
    override fun getValueFromPreferences(key: String?, defValue: Boolean?): Boolean {
        return sharedPrefs.getBoolean(key, defValue ?: false)
    }
}

class IntLiveSharedPreference(prefs: SharedPreferences?, key: String?, defValue: Int) :
    SharedPreferenceLiveData<Int>(prefs, key, defValue) {
    override fun getValueFromPreferences(key: String?, defValue: Int?): Int {
        return sharedPrefs.getInt(key, defValue ?: 0)
    }
}

class LongLiveSharedPreference(prefs: SharedPreferences?, key: String?, defValue: Long) :
    SharedPreferenceLiveData<Long>(prefs, key, defValue) {
    override fun getValueFromPreferences(key: String?, defValue: Long?): Long {
        return sharedPrefs.getLong(key, defValue ?: 0)
    }
}