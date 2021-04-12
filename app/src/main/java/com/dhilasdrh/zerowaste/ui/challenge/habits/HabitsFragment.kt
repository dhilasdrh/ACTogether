package com.dhilasdrh.zerowaste.ui.challenge.habits

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhilasdrh.zerowaste.R

class HabitsFragment : Fragment() {

    companion object {
        fun newInstance() = HabitsFragment()
    }

    private lateinit var viewModel: HabitsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.habits_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}