package com.dhilasdrh.zerowaste.ui.community.communitypost

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhilasdrh.zerowaste.R

class CommunityPostFragment : Fragment() {

    companion object {
        fun newInstance() = CommunityPostFragment()
    }

    private lateinit var viewModel: CommunityPostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_community_post, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommunityPostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}