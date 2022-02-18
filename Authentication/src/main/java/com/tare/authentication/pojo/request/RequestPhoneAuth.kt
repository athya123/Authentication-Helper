package com.tare.authentication.pojo.request


import com.google.gson.annotations.SerializedName

data class RequestPhoneAuth(
    @SerializedName("apikey")
    var apikey: String = "",
    @SerializedName("countrycode")
    var countrycode: String = "",
    @SerializedName("mid")
    var mid: String = "",
    @SerializedName("phone")
    var phone: String = ""
)