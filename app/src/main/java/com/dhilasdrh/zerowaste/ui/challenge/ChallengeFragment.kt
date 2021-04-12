package com.dhilasdrh.zerowaste.ui.challenge

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dhilasdrh.zerowaste.databinding.DialogChallengePopupBinding
import com.dhilasdrh.zerowaste.databinding.FragmentChallengeBinding

class ChallengeFragment : Fragment() {
    private lateinit var challengeViewModel: ChallengeViewModel
    private lateinit var binding: FragmentChallengeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChallengeBinding.inflate(layoutInflater, container, false)

      /*  val viewPagerAdapter = ChallengePagerAdapter(this, childFragmentManager)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)*/

        binding.cvCutFoodWaste.setOnClickListener {
            showPopup()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        challengeViewModel = ViewModelProvider(this).get(ChallengeViewModel::class.java)
    }

    private fun showPopup() {
        val popupView: DialogChallengePopupBinding = DialogChallengePopupBinding.inflate(layoutInflater)
        val popupWindow = PopupWindow(
            popupView.root,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.parseColor("#ffffff")))
        popupWindow.setElevation(10F)
        popupWindow.showAsDropDown(popupView.root, 0, 0)
        popupWindow.setOutsideTouchable(true)
        popupWindow.setFocusable(true)

        popupView.btnCancel.setOnClickListener {
            popupWindow.dismiss()
        }
    }
}