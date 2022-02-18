package com.tare.authentication.pojo.request


import com.google.gson.annotations.SerializedName

data class RequestGoogleAuth(
    @SerializedName("apikey")
    var apikey: String = "",
    @SerializedName("countrycode")
    var countrycode: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("fname")
    var fname: String = "",
    @SerializedName("lname")
    var lname: String = "",
    @SerializedName("mid")
    var mid: String = "",
    @SerializedName("phone")
    var phone: String = "",
    @SerializedName("regmode")
    var regmode: String = "",
    @SerializedName("regmodeid")
    var regmodeid: String = ""
)