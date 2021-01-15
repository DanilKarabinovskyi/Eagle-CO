package com.example.eaglecompany.fragments.splash_screen

import android.os.Handler
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import com.example.eaglecompany.MainActivity
import com.example.eaglecompany.R
import com.example.eaglecompany.fragments.telephone_form.TelephoneFormFragment

class SplashScreenFragmentViewModel:ViewModel() {
    fun toTelephonFragment(activity: MainActivity){
        Handler().postDelayed({
        val manager: FragmentManager? = activity.supportFragmentManager
        val transaction: FragmentTransaction? = manager?.beginTransaction()
        transaction!!.replace(R.id.fragment_container, TelephoneFormFragment())
        transaction.commit()
        }, 3000)
    }
}