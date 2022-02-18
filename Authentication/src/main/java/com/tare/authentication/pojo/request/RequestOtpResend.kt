package com.tare.authentication.pojo.request


import com.google.gson.annotations.SerializedName

data class RequestOtpResend(
    @SerializedName("apikey")
    var apikey: String = "",
    @SerializedName("uapi")
    var uapi: String = ""
)