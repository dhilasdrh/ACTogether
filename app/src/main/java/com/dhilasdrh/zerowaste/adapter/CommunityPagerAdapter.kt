package com.dhilasdrh.zerowaste.adapter

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.ui.community.CommunityFragment
import com.dhilasdrh.zerowaste.ui.community.communitypost.CommunityPostFragment
import com.dhilasdrh.zerowaste.ui.community.mypost.MyPostFragment

class CommunityPagerAdapter(private val mContext: CommunityFragment, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
/*
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = CommunityPostFragment()
            1 -> fragment = MyPostFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }*/

    @StringRes
    private val tabTitle = intArrayOf(R.string.title_community, R.string.my_post)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = CommunityPostFragment()
            1 -> fragment = MyPostFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(tabTitle[position])
    }
}