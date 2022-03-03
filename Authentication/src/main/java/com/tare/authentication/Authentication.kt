package com.tare.authentication

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.tare.authentication.network.RestClient
import com.tare.authentication.pojo.response.ResponseStartKey
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import androidx.lifecycle.LiveData
import com.tare.authentication.pojo.entities.User
import com.tare.authentication.pojo.request.*

private const val PREFERENCE_USER_STORE = "user__state"

class Authentication(private val context: Context) {
    private val services = RestClient.create()
    private var uApi: String = ""
    private var otp: String = ""
    private var apiKey: String = ""

    private val _state = MutableLiveData<AuthState>()
    val state: LiveData<AuthState>
        get() = _state

    fun initUserState() {
        val sharedPreferences =
            context.getSharedPreferences(PREFERENCE_USER_STORE, Context.MODE_PRIVATE)
        val login = sharedPreferences.getBoolean(PreferenceKeys.User_Logged_In, false)
        if (login) {
            _state.postValue(
                AuthState.UserLoggedIn(
                    User(
                        sharedPreferences.getString(PreferenceKeys.User_Id, ""),
                        sharedPreferences.getString(PreferenceKeys.User_Key, "")
                    )
                )
            )
        } else {
            _state.postValue(AuthState.Empty)
        }
    }

    fun signUpWithPhone(phoneNumber: String) {
        _state.postValue(AuthState.Loading)
        getStartKey().subscribe({ startKey ->
            if (startKey.status == "success") {
                phoneAuth(startKey.key, phoneNumber)
                apiKey = startKey.key
            } else {
                _state.postValue(AuthState.Error(startKey.key))
            }
        }, {
            _state.postValue(AuthState.Error(it.message!!))
        })
    }

    fun signUpWithGoogle(
        email: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        regMode: String,
        regmodeid: String
    ) {
        _state.postValue(AuthState.Loading)
        getStartKey().subscribe({ startKey ->
            if (startKey.status == "success") {
                googleAuth(email, firstName, lastName, phoneNumber, regMode, regmodeid)
                apiKey = startKey.key
            } else {
                _state.postValue(AuthState.Error(startKey.key))
            }
        }, {
            _state.postValue(AuthState.Error(it.message!!))
        })
    }

    private fun googleAuth(
        email: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        regMode: String,
        regmodeid: String
    ) {
        _state.postValue(AuthState.Loading)
        services.loginGoogle(
            RequestGoogleAuth(
                apiKey,
                "91",
                email,
                firstName,
                lastName,
                "123asd1231231231",
                phoneNumber,
                regMode,
                regmodeid
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.status == "success") {
                    _state.postValue(AuthState.SendOTP(it.otp))
                    uApi = it.apikey
                    otp = it.otp
                } else {
                    _state.postValue(AuthState.Error(it.message))
                }
            }, {
                _state.postValue(AuthState.Error(it.message!!))
            })
    }

    private fun getStartKey(): Single<ResponseStartKey> {
        return services.getStartKey(RequestInitial("123asd1231231231", "0"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    private fun phoneAuth(apiKey: String, phoneNumber: String) {
        _state.postValue(AuthState.Loading)
        services.loginPhone(
            RequestPhoneAuth(
                apiKey,
                "91",
                "123asd1231231231",
                phoneNumber
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.status == "success") {
                    _state.postValue(AuthState.SendOTP(it.otp))
                    uApi = it.apikey
                    otp = it.otp
                } else {
                    _state.postValue(AuthState.Error(it.message))
                }
            }, {
                _state.postValue(AuthState.Error(it.message!!))
            })
    }

    fun verifyOTP(userOtp: String) {
        _state.postValue(AuthState.Loading)
        services.otpValidate(
            RequestOtpValidate(
                apiKey,
                userOtp,
                uApi
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.status == "success") {
                    _state.postValue(AuthState.OTPVerified(User(it.userid, apiKey)))
                    val sharedPreferences =
                        context.getSharedPreferences(PREFERENCE_USER_STORE, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean(PreferenceKeys.User_Logged_In, true)
                    editor.putString(PreferenceKeys.User_Id, it.userid)
                    editor.putString(PreferenceKeys.User_Key, apiKey)
                    editor.apply()
                } else {
                    _state.postValue(AuthState.Error(it.message))
                }
            }, {
                _state.postValue(AuthState.Error(it.message!!))
            })
    }

    fun resendOTP() {
        _state.postValue(AuthState.Loading)
        services.otpResend(
            RequestOtpResend(
                apiKey,
                uApi
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.status == "success") {
                    _state.postValue(AuthState.OTPResend)
                } else
                    _state.postValue(AuthState.Error(it.message))
            }, {
                _state.postValue(AuthState.Error(it.message!!))
            })
    }


}