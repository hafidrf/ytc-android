package com.yureka.technology.ytc.ui.auth.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.data.model.login.LoginAccess
import com.yureka.technology.ytc.data.repository.AppRepository
import com.yureka.technology.ytc.util.isAnValidEmail

class SignInViewModel @ViewModelInject constructor(
    appRepository: AppRepository
) : ViewModel() {

    private val accessKey = "d986c63c-0982-46c6-8aed-2b16d72e1633"

    private val _loginAccess = MutableLiveData<LoginAccess>()

    private val _email = MutableLiveData<String>()
    val isEmailValid = Transformations.map(_email){
        it.isAnValidEmail()
    }

    val loginResponse = Transformations.switchMap(_loginAccess) {
        appRepository.login(it)
    }

    fun validateEmail(email: String){
        _email.value = email
    }

    fun login(userName: String, password: String) {
        _loginAccess.value = LoginAccess(
            access_key = accessKey,
            username = userName,
            password = password
        )
    }

    fun loginWithGooggle(userId: String) {
        //todo call api for login using google account
    }

}