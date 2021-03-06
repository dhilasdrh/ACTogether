package com.dhilasdrh.zerowaste.ui.shop.upcycleshop

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhilasdrh.zerowaste.R

class UpcycleShopFragment : Fragment() {

    companion object {
        fun newInstance() = UpcycleShopFragment()
    }

    private lateinit var viewModel: UpcycleShopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.upcycle_shop_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpcycleShopViewModel::class.java)
        // TODO: Use the ViewModel
    }

}