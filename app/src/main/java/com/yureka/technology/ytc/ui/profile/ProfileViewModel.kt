package com.yureka.technology.ytc.ui.profile

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ProfileViewModel @ViewModelInject constructor(sharedPreferences: SharedPreferences): ViewModel() {
    init {
        Timber.i("Injected shared prefs")
    }
}