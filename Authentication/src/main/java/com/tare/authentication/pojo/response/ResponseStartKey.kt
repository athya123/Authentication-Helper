package com.tare.authentication.pojo.response


import com.google.gson.annotations.SerializedName

data class ResponseStartKey(
    @SerializedName("key")
    var key: String = "",
    @SerializedName("status")
    var status: String = ""
)