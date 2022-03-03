package com.tare.loginapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.tare.authentication.AuthState
import com.tare.authentication.Authentication
import com.tare.loginapp.login.LoginFragment
import com.tare.loginapp.login.OTPFragment

class MainActivity : AppCompatActivity() {
    private val authentication = Authentication(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authentication.initUserState()
        setContentView(R.layout.activity_main)
        replaceFragment(LoginFragment())
        observer()
    }

    fun loginWithNumber(number: String){
        authentication.signUpWithPhone(number)
    }

    fun getAuth(): Authentication = authentication

    private fun observer() {
        authentication.state.observe(this) {
            when (it) {
                is AuthState.Loading -> {
                    findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
                }
                is AuthState.SendOTP -> {
                    findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
                    replaceFragment(OTPFragment())
                    Toast.makeText(this, "Please Enter Otp: ${it.otp}", Toast.LENGTH_LONG).show()
                }
                is AuthState.UserLoggedIn -> {
                    Toast.makeText(this,"User is already logged in", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,HomeActivity::class.java))
                }
                is AuthState.OTPVerified -> {
                    startActivity(Intent(this,HomeActivity::class.java))
                }
                is AuthState.UserLoginFirstTime -> {
                    Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,HomeActivity::class.java))
                }
                is AuthState.Error -> {
                    Toast.makeText(this, it.message,Toast.LENGTH_LONG).show()
                }
                is AuthState.Empty -> {}
                else -> {}
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            addToBackStack(null)
            commit()
        }
    }
}