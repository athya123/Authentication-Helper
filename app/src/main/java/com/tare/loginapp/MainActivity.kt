package com.tare.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.tare.authentication.AuthState
import com.tare.authentication.Authentication

class MainActivity : AppCompatActivity() {
//    private lateinit var editText: EditText
//    private val authentication = Authentication(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        authentication.initUserState()
        setContentView(R.layout.activity_main)
//        authentication.signUpWithPhone("7000897944")
//        observer()
//        editText = findViewById(R.id.etOtp)
//        findViewById<Button>(R.id.verify).setOnClickListener {
//            enterOtp()
//        }
    }

//    private fun enterOtp(){
//        authentication.verifyOTP(editText.text.toString())
//    }

//    private fun observer() {
//        authentication.state.observe(this) {
//            when (it) {
//                is AuthState.Loading -> {
//                    findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
//                }
//                is AuthState.SendOTP -> {
//                    findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
//                    Toast.makeText(this, "Please Enter Otp: ${it.otp}", Toast.LENGTH_LONG).show()
//                }
//                is AuthState.OTPVerified -> {
//                    Toast.makeText(this, "User Created with ${it.user.userId}, ${it.user.apiKey}", Toast.LENGTH_LONG).show()
//                }
//                is AuthState.UserLoggedIn -> {
//                    Toast.makeText(this,"User is already logged in", Toast.LENGTH_LONG).show()
//                }
//                is AuthState.UserLoginFirstTime -> {
//                    Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_LONG).show()
//                }
//                is AuthState.Error -> {
//                    Toast.makeText(this, it.message,Toast.LENGTH_LONG).show()
//                }
//                is AuthState.Empty -> {}
//            }
//        }
//    }
}