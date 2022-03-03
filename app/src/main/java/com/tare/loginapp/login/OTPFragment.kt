package com.tare.loginapp.login

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tare.authentication.AuthState
import com.tare.authentication.Authentication
import com.tare.loginapp.MainActivity
import com.tare.loginapp.R
import  com.tare.loginapp.databinding.FragmentOtpBinding
import com.tare.progress.Progress

class OTPFragment : Fragment() {
    private var countDownTimer: CountDownTimer? = null
    private lateinit var authentication: Authentication
    private lateinit var progress: Progress
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentOtpBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_otp, container, false)
        binding.lifecycleOwner = this
        binding.terms.text = getString(R.string.loginText)
        binding.otpEditText.setOnCompleteListener{
            authentication.verifyOTP(it)
            Toast.makeText(requireContext(),"Entered OTP: $it",Toast.LENGTH_LONG).show()
        }
        binding.BTSendOTP.setOnClickListener{
            authentication.resendOTP()
        }
        authentication = (requireActivity() as MainActivity).getAuth()
        progress = Progress(requireContext())
        progress.initProgress()
        startTimer(binding)
        observer()
        return binding.root
    }

    private fun observer(){
        authentication.state.observe(requireActivity()){
            when(it){
                is AuthState.Loading -> {
                    progress.showProgress()
                }
                is AuthState.OTPResend ->{
                    progress.hideProgress()
                    Toast.makeText(
                        requireContext(),
                        "OTP resend successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AuthState.OTPVerified ->{
                    progress.hideProgress()
                    Toast.makeText(
                        requireContext(),
                        "OTP verified Successfully ",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AuthState.UserLoggedIn ->{
                    progress.hideProgress()
                    Toast.makeText(
                        requireContext(),
                        "User Logged In Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AuthState.Error ->{
                    progress.hideProgress()
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

    private fun startTimer(binding: FragmentOtpBinding){
        countDownTimer = object: CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.countDownTV.text = getString(R.string.seconds, (millisUntilFinished/1000).toString())
            }

            override fun onFinish() {
                binding.countDownTV.visibility = View.INVISIBLE
                binding.BTSendOTP.visibility = View.VISIBLE
            }
        }
        countDownTimer?.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer?.cancel()
    }
}