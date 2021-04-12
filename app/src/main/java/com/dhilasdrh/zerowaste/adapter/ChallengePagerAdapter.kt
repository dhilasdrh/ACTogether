package com.dhilasdrh.zerowaste.adapter

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dhilasdrh.zerowaste.R
import com.dhilasdrh.zerowaste.ui.challenge.ChallengeFragment
import com.dhilasdrh.zerowaste.ui.challenge.habits.HabitsFragment
import com.dhilasdrh.zerowaste.ui.challenge.journey.JourneyFragment
import com.dhilasdrh.zerowaste.ui.challenge.leaderboard.LeaderboardFragment

class ChallengePagerAdapter(private val mContext: ChallengeFragment, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    @StringRes
    private val tabTitle = intArrayOf(R.string.journey, R.string.habits, R.string.leaderboard)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = JourneyFragment()
            1 -> fragment = HabitsFragment()
            2 -> fragment = LeaderboardFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return tabTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(tabTitle[position])
    }
}