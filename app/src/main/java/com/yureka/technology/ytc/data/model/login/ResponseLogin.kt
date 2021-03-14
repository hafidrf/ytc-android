package com.yureka.technology.ytc.data.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseLogin(
    @SerializedName("data") @Expose val data: Data
)
class Data(
    @SerializedName("id") @Expose val id: String,
    @SerializedName("username") @Expose val username: String,
    @SerializedName("gender") @Expose val gender: Gender,
    @SerializedName("role") @Expose val role: Role,
    @SerializedName("company") @Expose val company: Company,
    @SerializedName("email") @Expose val email: String,
    @SerializedName("firstname") @Expose val firstname: String,
    @SerializedName("lastname") @Expose val lastname: String,
    @SerializedName("status") @Expose val status: Status,
    @SerializedName("token") @Expose val token: String,
    @SerializedName("last_login") @Expose val last_login: String,
    @SerializedName("created_at") @Expose val created_at: String,
    @SerializedName("updated_at") @Expose val updated_at: String
)

class Gender(
    @SerializedName("value") @Expose val value: Int,
    @SerializedName("uselabelrname") @Expose val label: String
)

class Role(
    @SerializedName("id") @Expose val id: String,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("slug") @Expose val slug: String,
    @SerializedName("created_at") @Expose val created_at: String,
    @SerializedName("updated_at") @Expose val updated_at: String
)

class Company(
    @SerializedName("id") @Expose val id: String,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("description") @Expose val description: String,
    @SerializedName("email") @Expose val email: String,
    @SerializedName("image") @Expose val image: String,
    @SerializedName("phone_number") @Expose val phone_number: String,
    @SerializedName("access_key") @Expose val access_key: String,
    @SerializedName("created_at") @Expose val created_at: String,
    @SerializedName("updated_at") @Expose val updated_at: String
)

class Status(
    @SerializedName("value") @Expose val value: String,
    @SerializedName("label") @Expose val label: String
)

data class UserModel(
    val name: String, val email: String
)