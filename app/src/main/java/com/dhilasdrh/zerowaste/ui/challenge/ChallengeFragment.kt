package com.dhilasdrh.zerowaste.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dhilasdrh.zerowaste.adapter.ChallengePagerAdapter
import com.dhilasdrh.zerowaste.databinding.FragmentChallengeBinding

class ChallengeFragment : Fragment() {
    private lateinit var challengeViewModel: ChallengeViewModel
    private lateinit var binding: FragmentChallengeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChallengeBinding.inflate(layoutInflater, container, false)

        val viewPagerAdapter = ChallengePagerAdapter(this, childFragmentManager)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        challengeViewModel = ViewModelProvider(this).get(ChallengeViewModel::class.java)
    }
}