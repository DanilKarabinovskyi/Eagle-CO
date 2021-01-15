package com.example.eaglecompany.fragments.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.eaglecompany.R
import com.example.eaglecompany.databinding.MainScreenFragmentBinding
import com.example.eaglecompany.fragments.orders.OrdersFragment
import com.example.eaglecompany.fragments.splash_screen.SplachScreenFragment

class MainScreenFragment:Fragment(){
    private lateinit var binding: MainScreenFragmentBinding
    private lateinit var viewModel: MainScreenFragmentViewModel
    private lateinit var demoCollectionPagerAdapter: PagerAdapter
    private lateinit var viewPager: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(
            inflater,
            R.layout.main_screen_fragment,
            container,
            false)

        viewModel = ViewModelProvider(this).get(MainScreenFragmentViewModel::class.java)
        binding.verificationScreenViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        demoCollectionPagerAdapter = PagerAdapter(childFragmentManager)

        viewPager = binding.viewpager

        var firstFragment = OrdersFragment("worker")
        var secondFragment = OrdersFragment("carrier")
        var thirdFragment = OrdersFragment("porter")

        viewPager.adapter = demoCollectionPagerAdapter
        demoCollectionPagerAdapter.addFragment(firstFragment,"Вантажники")
        demoCollectionPagerAdapter.addFragment(secondFragment,"Перевізники")
        demoCollectionPagerAdapter.addFragment(thirdFragment,"Будівельники")

        demoCollectionPagerAdapter.notifyDataSetChanged()

        return binding.root
    }
}