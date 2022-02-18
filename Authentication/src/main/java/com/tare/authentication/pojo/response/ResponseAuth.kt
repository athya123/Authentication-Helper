package com.tare.authentication.pojo.response


import com.google.gson.annotations.SerializedName

data class ResponseAuth(
    @SerializedName("apikey")
    var apikey: String = "",
    @SerializedName("message")
    var message: String = "",
    @SerializedName("otp")
    var otp: String = "",
    @SerializedName("otpnote")
    var otpnote: String = "",
    @SerializedName("status")
    var status: String = ""
)