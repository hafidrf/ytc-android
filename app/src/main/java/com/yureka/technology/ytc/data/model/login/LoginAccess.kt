package com.yureka.technology.ytc.data.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginAccess(
    @SerializedName("access_key") @Expose var access_key: String? = null,
    @SerializedName("email") @Expose var username: String? = null,
    @SerializedName("password") @Expose var password: String? = null,
    @SerializedName("provider") @Expose var provider: String = "email"

)