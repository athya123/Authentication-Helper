package com.tare.authentication.pojo.response


import com.google.gson.annotations.SerializedName

data class ResponseOtpResend(
    @SerializedName("message")
    var message: String = "",
    @SerializedName("status")
    var status: String = ""
)