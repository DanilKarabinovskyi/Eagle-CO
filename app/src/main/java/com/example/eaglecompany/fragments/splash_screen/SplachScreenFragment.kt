package com.example.eaglecompany.fragments.splash_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eaglecompany.MainActivity
import com.example.eaglecompany.R
import com.example.eaglecompany.databinding.SplashScreenLayoutBinding

class SplachScreenFragment: Fragment() {

    private lateinit var binding:  SplashScreenLayoutBinding
    private lateinit var viewModel: SplashScreenFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(
            inflater,
            R.layout.splash_screen_layout,
            container,
            false)

        viewModel = ViewModelProvider(this).get(SplashScreenFragmentViewModel::class.java)
        binding.splashScreenViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.toTelephonFragment(requireActivity() as MainActivity)
        return binding.root
    }
}