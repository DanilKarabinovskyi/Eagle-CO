package com.example.eaglecompany.fragments.telephone_form

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.eaglecompany.MainActivity
import com.example.eaglecompany.SecondActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.*
import java.util.concurrent.TimeUnit

class TelephoneFormFragmentViewModel:ViewModel() {
    var auth = FirebaseAuth.getInstance()
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationId = ""
    private fun verificationCallbacks (inputTelephone: TextInputEditText, inputCode: TextInputEditText,
                                       verifyButton: Button, goInSystem:Button,progress:ProgressBar,context: Context) {
        mCallbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signIn(credential,activity = MainActivity())
                progress.visibility = View.INVISIBLE
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(context, "Oops. something wrong, please check your telephone number or internet connection", Toast.LENGTH_LONG).show()
                progress.visibility = View.INVISIBLE
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                verificationId = p0
                progress.visibility = View.INVISIBLE
                inputTelephone.visibility = View.INVISIBLE
                inputCode.visibility = View.VISIBLE
                verifyButton.visibility = View.INVISIBLE
                goInSystem.visibility = View.VISIBLE
            }
        }
    }
    fun verifyNumber(activity: MainActivity, phoneNumber:String,inputTelephone: TextInputEditText, inputCode: TextInputEditText,
                     verifyButton: Button, goInSystem:Button,progress:ProgressBar,context: Context){
        if(!phoneNumber.isBlank() && (phoneNumber.length > 10)) {
        auth.setLanguageCode(Locale.getDefault().language)
        verificationCallbacks(inputTelephone, inputCode,
            verifyButton, goInSystem,progress,context)

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                activity,
                mCallbacks
            )
        }
        else{
            Toast.makeText(context, "Wrong telephone input, please check it", Toast.LENGTH_LONG).show()
            progress.visibility = View.INVISIBLE
        }
    }
    private fun signIn (credential: PhoneAuthCredential, activity: MainActivity) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                    task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val intent = Intent(activity, SecondActivity::class.java)
                    activity.finish()
                    activity.startActivity(intent)
                }
            }
    }

    fun authenticate (inputCode: TextInputEditText, activity: MainActivity,context: Context) {

        val verifyNumber = inputCode.text.toString()
        if(!verifyNumber.isBlank() && (verifyNumber.length == 6)) {
            val credential: PhoneAuthCredential =
                PhoneAuthProvider.getCredential(verificationId, verifyNumber)
            signIn(credential, activity)
        }
        else{
            Toast.makeText(context, "Wrong code input, please check it", Toast.LENGTH_LONG).show()
        }
    }
}