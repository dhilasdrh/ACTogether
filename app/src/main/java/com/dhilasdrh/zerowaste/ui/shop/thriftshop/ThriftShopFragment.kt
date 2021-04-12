package com.dhilasdrh.zerowaste.ui.shop.thriftshop

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhilasdrh.zerowaste.R

class ThriftShopFragment : Fragment() {

    companion object {
        fun newInstance() = ThriftShopFragment()
    }

    private lateinit var viewModel: ThriftShopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.thrift_shop_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ThriftShopViewModel::class.java)
        // TODO: Use the ViewModel
    }

}