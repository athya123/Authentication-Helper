package com.tare.authentication.network

import com.tare.authentication.pojo.request.*
import com.tare.authentication.pojo.response.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface Services {

    @POST("accgetbaseurl")
    fun getBaseUrl(@Body requestInitial: RequestInitial): Single<ResponseBaseUrl>

    @POST("accgetstartapi")
    fun getStartKey(@Body requestInitial: RequestInitial): Single<ResponseStartKey>

    @POST("acclogin")
    fun loginPhone(@Body requestPhoneAuth: RequestPhoneAuth): Single<ResponseAuth>

    @POST("accloginext")
    fun loginGoogle(@Body requestGoogleAuth: RequestGoogleAuth): Single<ResponseAuth>

    @POST("accvalidate")
    fun otpValidate(@Body requestOtpValidate: RequestOtpValidate): Single<ResponseOtpValidate>

    @POST("accvalidate")
    fun otpResend(@Body requestOtpResend: RequestOtpResend): Single<ResponseOtpResend>
}