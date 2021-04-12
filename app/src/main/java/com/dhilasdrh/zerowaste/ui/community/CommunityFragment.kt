package com.dhilasdrh.zerowaste.ui.community

import android.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dhilasdrh.zerowaste.adapter.CommunityPagerAdapter
import com.dhilasdrh.zerowaste.databinding.DialogPostPopupBinding
import com.dhilasdrh.zerowaste.databinding.FragmentCommunityBinding


class CommunityFragment : Fragment() {

    companion object {
        fun newInstance() = CommunityFragment()
    }

    private lateinit var viewModel: CommunityViewModel
    private lateinit var binding: FragmentCommunityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityBinding.inflate(layoutInflater, container, false)

        val viewPagerAdapter = CommunityPagerAdapter(this, childFragmentManager)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.fab.setOnClickListener {
            showPopup()
        }
        return binding.root
    }

    private fun showPopup() {
        val popupView: DialogPostPopupBinding = DialogPostPopupBinding.inflate(layoutInflater)
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommunityViewModel::class.java)
    }

}