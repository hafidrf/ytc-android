package com.yureka.technology.ytc.ui.profile.registration

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.yureka.technology.ytc.util.isAnValidEmail
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.ui.profile.registration.model.ItemModel

class SignUpViewModel @ViewModelInject constructor() : ViewModel() {

    private val _allValid = MediatorLiveData<Boolean>()
    val allValid: LiveData<Boolean> = _allValid

    private val _emailAddress = MutableLiveData<String>()
    val isEmailValid = Transformations.map(_emailAddress) {
        it.isAnValidEmail()
    }

    private val _password = MutableLiveData<String>()
    val isPasswordValid = Transformations.map(_password) {
        it.length >= 6
    }

    private val _allIsNotBlank = MutableLiveData<Boolean>()

    init {

        _allValid.addSource(isEmailValid) {
            _allValid.value = it && isPasswordValid.value ?: false && _allIsNotBlank.value ?: false
        }

        _allValid.addSource(isPasswordValid) {
            _allValid.value = it && _allIsNotBlank.value ?: false && isEmailValid.value ?: false
        }

        _allValid.addSource(_allIsNotBlank) {
            _allValid.value = it && isPasswordValid.value ?: false && isEmailValid.value ?: false
        }


    }


    fun validate(name: String, email: String, password: String, phone: String, age: String) {
        _allIsNotBlank.value =
            name.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && age.isNotBlank()
    }

    fun validateEmail(email: String) {
        _emailAddress.value = email
    }

    fun validatePassword(password: String) {
        _password.value = password
    }

}