package com.dhilasdrh.zerowaste.ui.community.mypost

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhilasdrh.zerowaste.R

class MyPostFragment : Fragment() {

    companion object {
        fun newInstance() = MyPostFragment()
    }

    private lateinit var viewModel: MyPostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyPostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}