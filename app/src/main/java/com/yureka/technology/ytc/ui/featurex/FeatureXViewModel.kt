package com.yureka.technology.ytc.ui.featurex

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.yureka.technology.ytc.api.WebServiceApi
import com.yureka.technology.ytc.data.model.login.LoginAccess
import com.yureka.technology.ytc.data.model.login.ResponseLogin
import com.yureka.technology.ytc.ui.common.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FeatureXViewModel @ViewModelInject constructor(/*private val api: WebServiceApi*/) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val isOperationInProgress = ObservableField(false)
//    val message = ObservableField<Result<String>>()
    val message = ObservableField<Result<ResponseLogin>>()

//    fun onSendWebRequest(name: String) {
//        Timber.i("Sending web request with name: $name")
//
//        compositeDisposable.add(
//            api.hello(name)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe { isOperationInProgress.set(true) }
//                .doOnSuccess { isOperationInProgress.set(false) }
//                .doOnError { isOperationInProgress.set(false) }
//                .subscribe({
//                    message.set(Result.Success(it.messsage ?: ""))
//                }, { error ->
//                    Timber.e(error)
//                    message.set(Result.Error(error))
//
//                })
//        )
//    }

    fun onSendWebRequest() {
        var loginAccess = LoginAccess()
        loginAccess.access_key = "d986c63c-0982-46c6-8aed-2b16d72e1633"
        loginAccess.username = "ytc"
        loginAccess.password = "ytcYes"

        Timber.i("Sending web request with name: $loginAccess.access_key")
/*
        compositeDisposable.add(
            api.login(loginAccess)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isOperationInProgress.set(true) }
                .doOnSuccess { isOperationInProgress.set(false) }
                .doOnError { isOperationInProgress.set(false) }
                .subscribe({
                    message.set(Result.Success(it))
                }, { error ->
                    Timber.e(error)
                    message.set(Result.Error(error))

                })
        )*/
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
