package com.yureka.technology.ytc.ui.home.homefragments

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.ui.extensions.LiveEvent
import timber.log.Timber

class FragmentProfilViewModel  @ViewModelInject constructor(
    sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _featureRegistEvent = LiveEvent<Unit>()
    val featureRegistEvent: LiveData<Unit> = _featureRegistEvent
    private val _featureSignIn = LiveEvent<Unit>()
    val featureSignEvent: LiveData<Unit> = _featureSignIn

    fun openFeatureRegistEventClick() {
        _featureRegistEvent.value = Unit
    }
    fun openFeatureSigninEventClick() {
        _featureSignIn.value = Unit
    }

}