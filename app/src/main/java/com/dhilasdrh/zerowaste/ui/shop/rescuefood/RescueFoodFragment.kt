package com.dhilasdrh.zerowaste.ui.shop.rescuefood

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhilasdrh.zerowaste.R

class RescueFoodFragment : Fragment() {

    companion object {
        fun newInstance() = RescueFoodFragment()
    }

    private lateinit var viewModel: RescueFoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rescue_food_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RescueFoodViewModel::class.java)
        // TODO: Use the ViewModel
    }

}