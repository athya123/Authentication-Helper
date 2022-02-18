package com.tare.authentication.pojo.request


import com.google.gson.annotations.SerializedName

data class RequestInitial(
    @SerializedName("mid")
    var mid: String = "",
    @SerializedName("userid")
    var userid: String = ""
)