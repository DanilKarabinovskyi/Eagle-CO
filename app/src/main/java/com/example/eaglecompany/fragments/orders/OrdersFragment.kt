package com.example.eaglecompany.fragments.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eaglecompany.R
import com.example.eaglecompany.SecondActivity
import com.example.eaglecompany.databinding.OrderAfterLayoutBinding
import com.example.eaglecompany.databinding.OrdersFragmentLayoutBinding

class OrdersFragment(type:String) : Fragment() {
    private lateinit var binding:OrdersFragmentLayoutBinding
    private lateinit var viewModel: OrdersFragmentViewModel
    lateinit var  adapter: NewOrderAdapter
    val type = type
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.orders_fragment_layout,
            container,
            false)
        viewModel = ViewModelProvider(this).get(OrdersFragmentViewModel::class.java)
        binding.ordersViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        adapter = NewOrderAdapter(
            OrdersListener { lessonName ->
            lessonName.clicked = !lessonName.clicked
                adapter.notifyDataSetChanged()
            })
        binding.recycleList.adapter = adapter
        binding.progress.visibility = View.VISIBLE
        viewModel.getData(adapter,type,binding.noInfo,binding.progress,requireContext())

        val manager = GridLayoutManager(requireActivity() as SecondActivity, 1)
        binding.recycleList.layoutManager = manager
        setHasOptionsMenu(true)
        return binding.root
    }

}