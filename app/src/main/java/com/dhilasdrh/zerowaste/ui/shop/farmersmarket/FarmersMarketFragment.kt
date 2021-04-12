package com.dhilasdrh.zerowaste.ui.shop.farmersmarket

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhilasdrh.zerowaste.R

class FarmersMarketFragment : Fragment() {

    companion object {
        fun newInstance() = FarmersMarketFragment()
    }

    private lateinit var viewModel: FarmersMarketViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.farmers_market_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FarmersMarketViewModel::class.java)
        // TODO: Use the ViewModel
    }

}