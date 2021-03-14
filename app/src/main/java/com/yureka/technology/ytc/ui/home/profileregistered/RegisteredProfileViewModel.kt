package com.yureka.technology.ytc.ui.home.profileregistered

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.data.repository.AppRepository

/**
 * Created on 11/28/20.
 */

class RegisteredProfileViewModel @ViewModelInject constructor(
    repository: AppRepository
) : ViewModel() {

    private val _logout = MutableLiveData<Boolean>()
    val loggedOut = Transformations.switchMap(_logout) {
        repository.logout()
    }

    val userModel = repository.getUserModel()

    fun logout() {
        _logout.value = true
    }


}