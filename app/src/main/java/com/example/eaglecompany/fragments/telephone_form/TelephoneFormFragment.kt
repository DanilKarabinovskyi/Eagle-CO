package com.example.eaglecompany.fragments.telephone_form

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
import com.example.eaglecompany.databinding.TelephoneFormLayoutBinding
import com.example.eaglecompany.fragments.splash_screen.SplashScreenFragmentViewModel

class TelephoneFormFragment:Fragment() {
    private lateinit var binding: TelephoneFormLayoutBinding
    private lateinit var viewModel: TelephoneFormFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(
            inflater,
            R.layout.telephone_form_layout,
            container,
            false)

        viewModel = ViewModelProvider(this).get(TelephoneFormFragmentViewModel::class.java)
        binding.telephoneFormScreenViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.telephoneVerifyButton.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            viewModel.verifyNumber(
            requireActivity() as MainActivity,binding.inputTelephone.text.toString(),
            binding.inputTelephone,binding.inputCode,
            binding.telephoneVerifyButton,binding.toSystemButton,binding.progress,requireContext())
        }
        binding.toSystemButton.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            viewModel.authenticate(binding.inputCode,requireActivity() as MainActivity,requireContext())
        }
        return binding.root
    }
}