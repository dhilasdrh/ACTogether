package com.dhilasdrh.zerowaste.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dhilasdrh.zerowaste.adapter.ShopPagerAdapter
import com.dhilasdrh.zerowaste.databinding.FragmentShopBinding

class ShopFragment : Fragment() {
    private lateinit var viewModel: ShopViewModel
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentShopBinding.inflate(layoutInflater, container, false)

        val viewPagerAdapter = ShopPagerAdapter(this, childFragmentManager)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        return binding.root
    }
}