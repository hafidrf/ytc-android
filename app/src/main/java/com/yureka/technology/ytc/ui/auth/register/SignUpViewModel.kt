package com.yureka.technology.ytc.ui.auth.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.yureka.technology.ytc.data.model.RegisterRequest
import com.yureka.technology.ytc.data.repository.AppRepository
import com.yureka.technology.ytc.util.isAnValidEmail

class SignUpViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {

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

    private val _registerForm = MutableLiveData<RegisterRequest>()
    val registerResponse = Transformations.switchMap(_registerForm){
        repository.register(it)
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
            name.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && age.isNotBlank() && password.isNotBlank()
    }

    fun validateEmail(email: String) {
        _emailAddress.value = email
    }

    fun validatePassword(password: String) {
        _password.value = password
    }

    fun submmit(name: String, email: String, password: String, phone: String, age: String){
        _registerForm.value = RegisterRequest(
            name = name,
            email = email,
            age = age,
            password = password,
            phone = phone
        )
    }

}