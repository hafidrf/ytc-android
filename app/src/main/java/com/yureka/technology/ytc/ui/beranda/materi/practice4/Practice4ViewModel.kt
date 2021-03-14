package com.yureka.technology.ytc.ui.beranda.materi.practice4

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import timber.log.Timber

class Practice4ViewModel @ViewModelInject constructor(sharedPreferences: SharedPreferences) :
    ViewModel() {
    init {
        Timber.i("Injected shared prefs")
    }
}