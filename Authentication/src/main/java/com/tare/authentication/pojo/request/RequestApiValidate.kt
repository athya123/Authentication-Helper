package com.tare.authentication.pojo.request


import com.google.gson.annotations.SerializedName

data class RequestApiValidate(
    @SerializedName("apikey")
    var apikey: String = "",
    @SerializedName("userid")
    var userid: String = ""
)