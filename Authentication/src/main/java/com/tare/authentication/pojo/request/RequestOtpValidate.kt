package com.tare.authentication.pojo.request


import com.google.gson.annotations.SerializedName

data class RequestOtpValidate(
    @SerializedName("apikey")
    var apikey: String = "",
    @SerializedName("otp")
    var otp: String = "",
    @SerializedName("uapi")
    var uapi: String = ""
)