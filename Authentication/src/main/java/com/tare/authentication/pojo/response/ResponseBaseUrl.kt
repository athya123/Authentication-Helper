package com.tare.authentication.pojo.response


import com.google.gson.annotations.SerializedName

data class ResponseBaseUrl(
    @SerializedName("baseurl")
    var baseurl: String = "",
    @SerializedName("status")
    var status: String = ""
)