package com.yureka.technology.ytc.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.data.repository.AppRepository

class HomeViewModel @ViewModelInject constructor(
    repository: AppRepository
) : ViewModel() {

    val isLoggedIn = repository.isLoggedIn()

    val weekPassed = repository.getWeekPassed()

}
