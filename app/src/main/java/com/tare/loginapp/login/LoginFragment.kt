package com.tare.loginapp.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.tare.authentication.AuthState
import com.tare.authentication.Authentication
import com.tare.loginapp.MainActivity
import com.tare.loginapp.R
import com.tare.loginapp.custom.makeHyperLink
import com.tare.loginapp.databinding.FragmentLoginBinding
import com.tare.progress.Progress


@SuppressLint("ClickableViewAccessibility")
class LoginFragment : Fragment() {
    private lateinit var authentication: Authentication
    private lateinit var progress: Progress
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this
        binding.termsCondition.makeHyperLink("https://www.google.com")
        setLogin(binding)
        googleInit()
        binding.googleSignIn.setSize(SignInButton.SIZE_WIDE)
        binding.googleSignIn.setOnClickListener {
            resultLauncher.launch(Intent(googleSignInClient.signInIntent))
        }
        authentication = (requireActivity() as MainActivity).getAuth()
        progress = Progress(requireContext())
        progress.initProgress()
        observer()
        return binding.root
    }

    private fun observer() {
        authentication.state.observe(requireActivity()) {
            when (it) {
                is AuthState.Loading -> {
                    progress.showProgress()
                }
                is AuthState.SendOTP -> {
                    progress.hideProgress()
                    Toast.makeText(
                        requireContext(),
                        "OTP Sent Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AuthState.Error -> {
                    progress.hideProgress()
                    Toast.makeText(
                        requireContext(),
                        "Error: ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data!!
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                    authentication.signUpWithGoogle(
                        account.email!!,
                        account.displayName!!,
                        account.givenName!!,
                        account.id!!,
                        "google",
                        ""
                    )
                } catch (e: ApiException) {

                }
            }
        }

    private fun setLogin(binding: FragmentLoginBinding) {
        binding.phoneLogin.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_UP) {
                    if (event.rawX >= (binding.phoneLogin.right - binding.phoneLogin.compoundDrawables[2].bounds.width())) {
                        if (binding.phoneLogin.text.length != 10) {
                            Toast.makeText(
                                requireContext(),
                                "Please enter a 10 digit mobile number",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            (requireActivity() as MainActivity).loginWithNumber(binding.phoneLogin.text.toString())
                        }
                        return true
                    }
                }
                return false
            }

        })
    }

    private fun googleInit() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }
}