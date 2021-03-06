package com.tare.authentication

import com.tare.authentication.pojo.entities.User

sealed class AuthState{
    object Loading: AuthState()
    object Empty: AuthState()
    data class Error(val message: String): AuthState()
    data class SendOTP(val otp: String): AuthState()
    data class OTPVerified(val user: User): AuthState()
    data class UserLoggedIn(val user: User): AuthState()
    object OTPResend: AuthState()
    object UserLoginFirstTime: AuthState()
}
