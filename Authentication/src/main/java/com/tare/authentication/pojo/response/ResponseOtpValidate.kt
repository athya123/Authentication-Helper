package com.tare.authentication.pojo.response


import com.google.gson.annotations.SerializedName

data class ResponseOtpValidate(
    @SerializedName("expiredays ")
    var expiredays: String = "",
    @SerializedName("message")
    var message: String = "",
    @SerializedName("profiles")
    var profiles: Map<Int,MapValue> = mapOf(),
    @SerializedName("status")
    var status: String = "",
    @SerializedName("userid")
    var userid: String = ""
) {
    data class MapValue(
        @SerializedName("cashaccepted")
        var cashaccepted: Any? = null,
        @SerializedName("createdby")
        var createdby: String = "",
        @SerializedName("createdon")
        var createdon: String = "",
        @SerializedName("creditcardaccepted")
        var creditcardaccepted: Any? = null,
        @SerializedName("deletedby")
        var deletedby: Any? = null,
        @SerializedName("deletedon")
        var deletedon: Any? = null,
        @SerializedName("deliveryavailable")
        var deliveryavailable: String = "",
        @SerializedName("deliverycharges")
        var deliverycharges: String = "",
        @SerializedName("deliveryfreevalue")
        var deliveryfreevalue: String = "",
        @SerializedName("gstapplicable")
        var gstapplicable: String = "",
        @SerializedName("gstno")
        var gstno: String = "",
        @SerializedName("gstrate")
        var gstrate: String = "",
        @SerializedName("id")
        var id: String = "",
        @SerializedName("image")
        var image: Any? = null,
        @SerializedName("name")
        var name: String = "",
        @SerializedName("netbankingaccepted")
        var netbankingaccepted: Any? = null,
        @SerializedName("orderautoaccept")
        var orderautoaccept: String = "",
        @SerializedName("status")
        var status: String = "",
        @SerializedName("type")
        var type: String = "",
        @SerializedName("typeid")
        var typeid: String = "",
        @SerializedName("upiaccepted")
        var upiaccepted: Any? = null,
        @SerializedName("url")
        var url: Any? = null,
        @SerializedName("userid")
        var userid: String = "",
        @SerializedName("walletsaccepted")
        var walletsaccepted: Any? = null
    )
}