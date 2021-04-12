package com.dhilasdrh.zerowaste.ui.shop.zerowasteshop

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhilasdrh.zerowaste.R

class ZeroWasteShopFragment : Fragment() {

    companion object {
        fun newInstance() = ZeroWasteShopFragment()
    }

    private lateinit var viewModel: ZeroWasteShopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.zero_waste_shop_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ZeroWasteShopViewModel::class.java)
        // TODO: Use the ViewModel
    }

}